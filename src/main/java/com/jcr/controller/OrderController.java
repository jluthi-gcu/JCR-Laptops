package com.jcr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jcr.model.CustomerModel;
import com.jcr.model.OrderModel;
import com.jcr.model.ProductModel;
import com.jcr.service.CustomerService;
import com.jcr.service.OrderService;
import com.jcr.service.ProductService;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders/")
public class OrderController {

	private final OrderService orderService;
	private final CustomerService customerService;
	private final ProductService productService;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public OrderController(OrderService orderService, CustomerService customerService,
			ProductService productService) {
		this.orderService = orderService;
		this.customerService = customerService;
		this.productService = productService;
	}


	@GetMapping("/")
	public String getAllOrders(Model model,
			@RequestParam(value = "sortOption", required = false) String sortOption) {

		//Log
		logger.info("Entering OrderController.getAllOrders()");
		
		List<OrderModel> orders = this.orderService.getAllOrders();

		// Fetch full customer and product details for each order
		for (OrderModel order : orders) {
			CustomerModel customer = customerService.getCustomerById(order.getCustomer().getCustomer_id());
			ProductModel product = productService.getProductById(order.getProduct().getProduct_id());
			order.setCustomer(customer);
			order.setProduct(product);
		}

		

		model.addAttribute("orders", orders);
		model.addAttribute("title", "Placed Orders");
		
		//Log
		logger.info("Exiting OrderController.getAllOrders()");
		
		return "orders";
	}

	@GetMapping("/create")
	public String showCreateForm(Model model) {
		

		//Log
		logger.info("Entering OrderController.showCreateForm()");
		
		model.addAttribute("order", new OrderModel());

		List<CustomerModel> customers = customerService.getAllCustomers();
		List<ProductModel> products = productService.getAllProducts();

		model.addAttribute("customers", customers);
		model.addAttribute("products", products);
		

		//Log
		logger.info("Exiting OrderController.showCreateForm()");
		
		return "addOrder";
	}

	@PostMapping("/create")
	public String doCreateOrder(@ModelAttribute @Valid OrderModel order,
			BindingResult bindingResult) {
		
		//Log
		logger.info("Entering OrderController.doCreateOrder()");
		
		if (bindingResult.hasErrors()) {
			return "addOrder";
		}

		// Fetch full customer and product objects based on received IDs.
		CustomerModel customer = customerService.getCustomerById(order.getCustomer().getCustomer_id());
		ProductModel product = productService.getProductById(order.getProduct().getProduct_id());

		// Set the fetched customer and product objects to the order model.
		order.setCustomer(customer);
		order.setProduct(product);

		order.setOrder_date(LocalDateTime.now());
		
		orderService.addWorkout(order);
		
		//Log
		logger.info("Exiting OrderController.doCreateOrder()");
		
		return "redirect:/orders/";
	}

	@GetMapping("/edit/{order_id}")
	public String showEditForm(@PathVariable Long order_id, Model model, RedirectAttributes redirectAttributes) {
		
		//Log
		logger.info("Entering OrderController.showEditForm()");
		
		OrderModel order = orderService.getOrderById(order_id);
		if (order == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "No order found with ID: " + order_id);
			return "redirect:/orders/";
		}
		model.addAttribute("order", order);

		// Fetch available customers and products for the dropdowns
		List<CustomerModel> customers = customerService.getAllCustomers();
		List<ProductModel> products = productService.getAllProducts();
		
		// Add them to the model
		model.addAttribute("customers", customers);
		model.addAttribute("products", products);

		System.out.println("Order ID: " + order.getOrder_id());
		
		//Log
		logger.info("Exiting OrderController.showEditForm()");
		
		return "editOrder";
	}

	@PostMapping("/edit/{order_id}")
	public String doEditOrder(@PathVariable Long order_id,
			@ModelAttribute @Valid OrderModel order, BindingResult bindingResult) {
		
		//Log
		logger.info("Exiting OrderController.doEditOrder()");
		
		if (bindingResult.hasErrors()) {
			return "editOrder";
		}
		
		order.setOrder_date(LocalDateTime.now());
		
		orderService.editOrder(order);
		
		//Log
		logger.info("Exiting OrderController.doEditOrder()");
		
		return "redirect:/orders/";
	}

	@GetMapping("/delete/{order_id}")
	public String deleteOrder(@PathVariable Long order_id) {
		
		//Log
		logger.info("Entering OrderController.deleteOrder()");
		
		orderService.deleteOrder(order_id);
		
		//Log
		logger.info("Exiting OrderController.deleteOrder()");
		
		return "redirect:/orders/";
	}
}
