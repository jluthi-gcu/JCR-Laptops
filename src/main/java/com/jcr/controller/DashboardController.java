package com.jcr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jcr.model.CustomerModel;
import com.jcr.model.OrderModel;
import com.jcr.model.ProductModel;
import com.jcr.service.CustomerService;
import com.jcr.service.OrderService;
import com.jcr.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Controller responsible for displaying the user's dashboard which provides a
 * quick glance of recent students, classes, and enrollments
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	Logger logger = LoggerFactory.getLogger(getClass());
	

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;

	

	/**
	 * Fetches and displays relevant data for the user's dashboard, including recent
	 * students, classes, and enrollments
	 *
	 * @param model The model used to pass attributes to the view.
	 * @return The template name for displaying the dashboard.
	 */
	@GetMapping("/")
	public String showDashboard(Model model) {
	
		//Log
		logger.info("Entering DashboardController.showDashboard()");
		
		
		// Fetch the last 5 students and classes
		List<OrderModel> recentOrders = orderService.getFiveMostRecentOrders();


		// Fetch full customer and product details for each order
		for (OrderModel order : recentOrders) {
			CustomerModel customer = customerService.getCustomerById(order.getCustomer().getCustomer_id());
			ProductModel product = productService.getProductById(order.getProduct().getProduct_id());
			order.setCustomer(customer);
			order.setProduct(product);
		}
		
		
		// Add all the fetched data to the model
		model.addAttribute("recentOrders", recentOrders);
		
		//Log
		logger.info("Exiting DashboardController.showDashboard()");


		return "dashboard";
	}

}
