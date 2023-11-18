package com.jcr.controller;

import com.jcr.model.CustomerModel;
import com.jcr.service.CustomerService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Controller responsible for handling CRUD operations related to customers.
 * 
 * @version 1.0
 */
@Controller
@RequestMapping("/customers/")
public class CustomerController {


	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Service responsible for handling business logic related to customers. It's
	 * automatically injected by Spring Framework when an instance of this
	 * controller is created.
	 */
	@Autowired
	private CustomerService customerService;

	// A map defining ways to sort the customers
	private static final Map<String, Comparator<CustomerModel>> SORTING_METHODS = Map.of("first_name",
			Comparator.comparing(CustomerModel::getFirst_name), "last_name",
			Comparator.comparing(CustomerModel::getLast_name));
	
	
	/**
	 * Fetch all customers from the database and display them.
	 *
	 * @param	model The model used to pass attributes to the view.
	 * @return The template name for displaying all customers.
	 */
	@GetMapping
	public String getAllCustomers(Model model,
			@RequestParam(value = "sortOption", required = false) String sortOption) {
		
		//Log
		logger.info("Entering CustomerController.getAllCustomers()");
		
		
		List<CustomerModel> customers = customerService.getAllCustomers();
		
		// Clone the list for sorting
		List<CustomerModel> sortedCustomers = new ArrayList<>(customers);
		// If a valid sorting method is requested, sort the customers
		if (sortOption != null && SORTING_METHODS.containsKey(sortOption)) {
			sortedCustomers.sort(SORTING_METHODS.get(sortOption));
		}
		
		model.addAttribute("customers", sortedCustomers);
		model.addAttribute("title", "Your Customers");
		
		//Log
		logger.info("Exiting CustomerController.getAllCustomers()");
		
		return "customers"; // Template is named "customers.html"
	}

	/**
	 * Fetch a specific customer by its ID and display its details.
	 *
	 * @param	customer_id    The ID of the customer to retrieve.
	 * @param	model	The model used to pass attributes to the view.
	 * @return The template name for displaying customer details.
	 */
	@GetMapping("/{customer_id}")
	public String getCustomerById(@PathVariable("customer_id") Long customer_id, Model model) {
		
		//Log
		logger.info("Entering CustomerController.getCustomerById()");
		
		CustomerModel customer = customerService.getCustomerById(customer_id);
		if (customer != null) {
			model.addAttribute("customer", customer);
			return "customers"; // Template named "customers.html"
		}
		model.addAttribute("errorMessage", "Customer not found");
		
		//Log
		logger.info("Exiting CustomerController.getCustomerById()");
		
		return "customers";
	}

	/**
	 * Show a form for adding a new customer.
	 *
	 * @param	model	The model used to pass attributes to the view.
	 * @return The template name for the add customer form.
	 */
	@GetMapping("/create")
	public String showAddCustomerForm(Model model) {
		
		//Log
		logger.info("Entering CustomerController.showAddCustomerForm()");
		
		CustomerModel newCustomer = new CustomerModel();
		model.addAttribute("customer", newCustomer);
		
		//Log
		logger.info("Exiting CustomerController.showAddCustomerForm()");
		
		return "addCustomer";
	}

	/**
	 * Process the addition of a new customer.
	 *
	 * @param customer	The new customer to be added.
	 * @param result	Results of binding and validation.
	 * @param model 	The model used to pass attributes to the view.
	 * @return Redirects to the list of customers or shows the form again with errors.
	 */
	@PostMapping
	public String addcustomer(@Valid @ModelAttribute("customer") CustomerModel customer, BindingResult result, Model model) {
		
		
		//Log
		logger.info("Entering CustomerController.addcustomer()");
		
		if (result.hasErrors()) {
			return "addCustomer"; // Show the form again with error messages
		}
		customerService.addCustomer(customer);
		model.addAttribute("successMessage", "Customer added successfully!");
		
		//Log
		logger.info("Exiting CustomerController.addcustomer()");
		
		return "redirect:/customers/";
	}

	/**
	 * Show a form for editing a customer by its ID.
	 *
	 * @param customer_id                 The ID of the customer to edit.
	 * @param model              The model used to pass attributes to the view.
	 * @param redirectAttributes	Redirect attributes for flash messages.
	 * @return The template name for the edit customer form or a redirect.
	 */
	@GetMapping("/edit/{customer_id}")
	public String showEditcustomerForm(@PathVariable Long customer_id, Model model, RedirectAttributes redirectAttributes) {
		
		//Log
		logger.info("Entering CustomerController.showEditcustomerForm()");
		
		CustomerModel customer = customerService.getCustomerById(customer_id);
		if (customer_id == null) {
			// If the customer is not found, send an error message and redirect
			redirectAttributes.addFlashAttribute("errorMessage", "No customer found with ID: " + customer_id);
			return "redirect:/customers/";
		}
		// Add the customer to the model for editing
		model.addAttribute("customer", customer);
		
		
		//Log
		logger.info("Exiting CustomerController.showEditcustomerForm()");
		
		return "editCustomer"; // Template named "editCustomer.html"
	}

	/**
	 * Process the edits for a specific customer.
	 *
	 * @param customer_id            The ID of the customer to be edited.
	 * @param customer        The edited customer details.
	 * @param bindingResult Results of binding and validation.
	 * @return Redirects to the list of customers or shows the edit form again with
	 *         errors.
	 */
	@PostMapping("/edit/{customer_id}")
	public String doEditCustomer(@PathVariable Long customer_id, @ModelAttribute @Valid CustomerModel customer,
			BindingResult bindingResult) {
		
		//Log
		logger.info("Entering CustomerController.showEditcustomerForm()");
		
		// Validate the customer edits
		if (bindingResult.hasErrors()) {
			return "editCustomer"; // Go back to the form if there are validation errors
		}
		customer.setCustomer_id(customer_id); // Set the customer_id to ensure you're updating the correct customer
		customerService.editCustomer(customer); // Apply the edits
		
		//Log
		logger.info("Exiting CustomerController.showEditcustomerForm()");
		
		return "redirect:/customers/"; // Redirect to the list of customers
	}

	/**
	 * Deletes a customer by its ID.
	 *
	 * @param id The ID of the customer to delete.
	 * @return Redirects to the list of customers.
	 */
	@GetMapping("/delete/{customer_id}")
	public String deleteCustomer(@PathVariable Long customer_id) {
		
		//Log
		logger.info("Entering CustomerController.deleteCustomer()");
		
		customerService.deleteCustomer(customer_id);
		
		
		//Log
		logger.info("Exiting CustomerController.deleteCustomer()");
		
		return "redirect:/customers/";
	}
}
