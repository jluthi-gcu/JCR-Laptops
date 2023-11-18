package com.jcr.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a customer with attributes. This model is
 * also equipped with validation annotations for the name and email attributes.
 * 
 * @version 1.0
 */
public class OrderModel {

	/** Unique identifier for the order */
	private Long order_id;
	

	// Reference to the customer doing the ordering
	@NotNull(message = "Customer is required.")
	private CustomerModel customer;

	// Reference to the product being ordered
	@NotNull(message = "Product is required.")
	private ProductModel product;

	
	@NotNull(message = "Quantity is required.")
	private int quantity = 0;
	
	
	private LocalDateTime order_date;
	
	



	/** Default constructor */
	public OrderModel() {
	}



	// Constructor with all attributes
	public OrderModel(Long order_id, CustomerModel customer, ProductModel product, int quantity, LocalDateTime order_date ) {
		this.order_id = order_id;
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
		this.order_date = order_date;

	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}



	public CustomerModel getCustomer() {
		return customer;
	}



	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}



	public ProductModel getProduct() {
		return product;
	}



	public void setProduct(ProductModel product) {
		this.product = product;
	}



	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDateTime order_date) {
		this.order_date = order_date;
	}
	
	
	// Special Getter for formatting date/time
	public String getOrder_date_formatted() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy '@'hh:mm-a");
		 return order_date.format(formatter);
	
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", customer_id=" + customer.getCustomer_id() + ", product_id=" + product.getProduct_id() + ", quantity=" + quantity + ", order_date=" + order_date +  "]";
	}
}
