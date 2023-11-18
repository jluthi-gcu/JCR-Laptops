package com.jcr.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.jcr.model.ProductModel;
import com.jcr.service.ProductService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/products/")
public class ProductController {


	// A service for fetching and manipulating products
	private final ProductService productService;
	
	
	
	Logger logger = LoggerFactory.getLogger(getClass());

	// A map defining ways to sort the products
	private static final Map<String, Comparator<ProductModel>> SORTING_METHODS = Map.of("name",
			Comparator.comparing(ProductModel::getName), "price_asc",
			Comparator.comparing(ProductModel::getPrice), "price_desc",
			Comparator.comparing(ProductModel::getPrice));

	// Constructor-based dependency injection for the product service
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Fetch all products from the database and display them.
	 *
	 * @param	model The model used to pass attributes to the view.
	 * @return The template name for displaying all products.
	 */
	@GetMapping
	public String getAllCustomers(Model model, @RequestParam(value = "sortOption", required = false) String sortOption) {
		
		//Log
		logger.info("Entering ProductController.getAllCustomers()");
		
		List<ProductModel> products = productService.getAllProducts();
		
		// Clone the list for sorting
		List<ProductModel> sortedProducts = new ArrayList<>(products);
		// If a valid sorting method is requested, sort the customers
		if (sortOption != null && SORTING_METHODS.containsKey(sortOption)) {
			
			if (sortOption.equals("price_desc")) {
				sortedProducts.sort(SORTING_METHODS.get(sortOption).reversed());
			}else {
				sortedProducts.sort(SORTING_METHODS.get(sortOption));
			}
	
		}
		
		model.addAttribute("products", sortedProducts);
		model.addAttribute("title", "Your Laptops");
		
		//Log
		logger.info("Exiting ProductController.getAllCustomers()");
		
		return "products"; // Template is named "products.html"
	}

	// Display the form to create a new product
	@GetMapping("/create")
	public String showCreateForm(Model model) {
		
		//Log
		logger.info("Entering ProductController.showCreateForm()");
		
		model.addAttribute("product", new ProductModel());
		
		//Log
		logger.info("Exiting ProductController.showCreateForm()");
		
		return "addProduct";
	}

	// Process the form to create a new product
	@PostMapping("/create")
	public String doCreateProduct(@ModelAttribute @Valid ProductModel product, BindingResult bindingResult) {
		
		//Log
		logger.info("Entering ProductController.doCreateProduct()");
		
		// Validate the product
		if (bindingResult.hasErrors()) {
			return "addProduct"; // Go back to the form if there are validation errors
		}
		productService.addProduct(product); // Add the new product
		
		
		//Log
		logger.info("Exiting ProductController.doCreateProduct()");
		
		return "redirect:/products/"; // Redirect to the list of products
	}

	/**
	 * Show a form for editing a product by its ID.
	 *
	 * @param product_id                 The ID of the product to edit.
	 * @param model              The model used to pass attributes to the view.
	 * @param redirectAttributes	Redirect attributes for flash messages.
	 * @return The template name for the edit product form or a redirect.
	 */
	@GetMapping("/edit/{product_id}")
	public String showEditProductForm(@PathVariable Long product_id, Model model, RedirectAttributes redirectAttributes) {
		
		
		//Log
		logger.info("Entering ProductController.showEditProductForm()");
		
		ProductModel product = productService.getProductById(product_id);
		if (product_id == null) {
			// If the product is not found, send an error message and redirect
			redirectAttributes.addFlashAttribute("errorMessage", "No product found with ID: " + product_id);
			return "redirect:/products/";
		}
		// Add the product to the model for editing
		model.addAttribute("product", product);
		
		
		//Log
		logger.info("Exiting ProductController.showEditProductForm()");
		
		return "editProduct"; // Template named "editProduct.html"
	}

	/**
	 * Process the edits for a specific product.
	 *
	 * @param product_id            The ID of the product to be edited.
	 * @param product        The edited product details.
	 * @param bindingResult Results of binding and validation.
	 * @return Redirects to the list of products or shows the edit form again with
	 *         errors.
	 */
	@PostMapping("/edit/{product_id}")
	public String doEditProduct(@PathVariable Long product_id, @ModelAttribute @Valid ProductModel product,
			BindingResult bindingResult) {
		
		
		//Log
		logger.info("Entering ProductController.doEditProduct()");
		
		// Validate the product edits
		if (bindingResult.hasErrors()) {
			return "editProduct"; // Go back to the form if there are validation errors
		}
		product.setProduct_id(product_id); // Set the product_id to ensure you're updating the correct product
		productService.editProduct(product); // Apply the edits
		
		//Log
		logger.info("Exiting ProductController.doEditProduct()");
		
		return "redirect:/products/"; // Redirect to the list of products
	}

	/**
	 * Deletes a product by its ID.
	 *
	 * @param id The ID of the product to delete.
	 * @return Redirects to the list of products.
	 */
	@GetMapping("/delete/{product_id}")
	public String deleteProduct(@PathVariable Long product_id) {
		
		//Log
		logger.info("Entering ProductController.deleteProduct()");
		
		productService.deleteProduct(product_id);
		
		//Log
		logger.info("Exiting ProductController.deleteProduct()");
		
		return "redirect:/products/";
	}
}
