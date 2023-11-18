package com.jcr.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a customer with attributes. This model is
 * also equipped with validation annotations for the name and email attributes.
 * 
 * @version 1.0
 */
public class ProductModel {

	/** Unique identifier for the product */
	private Long product_id;

	
	@NotNull(message = "Name is required.")
	@Size(min = 3, max = 50, message = "Name should be between 3 to 50 characters.")
	private String name;

	
	@NotNull(message = "Description is required.")
	@Size(min = 1, max = 255, message = "Description should be between 1 to 255 characters.")
	private String description = "";
	
	
	@NotNull(message = "Price is required.")
	private float price = 0;
	
	
	@NotNull(message = "Quantity is required.")
	private int quantity = 0;
	



	/** Default constructor */
	public ProductModel() {
	}

	/**
	 * Overloaded constructor to initialize product attributes.
	 *
	 * @param product_id	Unique identifier for the product.
	 * @param name	The name of the product.
	 * @param description		The description of the product.
	 * @param price		The price of the product.
	 * @param quantity		The quantity of the product.
	 */
	public ProductModel(Long product_id, String name, String description, float price, int quantity) {
		this.product_id = product_id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;

	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getPriceString() {
		return "$" + this.price;
	}

	@Override
	public String toString() {
		return "(Id:" + product_id + ") "  + name + " - " + description + " | $" + price;
	}
}
