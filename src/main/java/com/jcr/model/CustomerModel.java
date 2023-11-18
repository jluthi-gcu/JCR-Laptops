package com.jcr.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a customer with attributes for ID, name, and email. This model is
 * also equipped with validation annotations for the name and email attributes.
 * 
 * @version 1.0
 */
public class CustomerModel {

	/** Unique identifier for the customer */
	private Long customer_id;

	/**
	 * The first name of the customer. Validation ensures the name is neither null nor
	 * outside the range of 3 to 50 characters.
	 */
	@NotNull(message = "First Name is required.")
	@Size(min = 3, max = 50, message = "First Name should be between 3 to 50 characters.")
	private String first_name;

	
	/**
	 * The last name of the customer. Validation ensures the name is neither null nor
	 * outside the range of 3 to 50 characters.
	 */
	@NotNull(message = "Last Name is required.")
	@Size(min = 3, max = 50, message = "Last Name should be between 3 to 50 characters.")
	private String last_name;
	
	/**
	 * The email address of the customer used for communications. Validation ensures
	 * the email is neither null nor outside the range of 5 to 100 characters.
	 */
	@NotNull(message = "Email is required.")
	@Size(min = 5, max = 50, message = "Email should be between 5 to 50 characters.")
	private String email;
	
	
	
	/**
	 * The email address of the customer used for communications. Validation ensures
	 * the email is neither null nor outside the range of 5 to 100 characters.
	 */
	@NotNull(message = "Phone is required.")
	@Size(min = 1, max = 10, message = "Phone should be at least 10 characters.")
	private String phone;
	
	
	/**
	 * The email address of the customer used for communications. Validation ensures
	 * the email is neither null nor outside the range of 5 to 100 characters.
	 */
	@NotNull(message = "Address is required.")
	@Size(min = 1, max = 255, message = "Please enter an address")
	private String address;
	



	/** Default constructor */
	public CustomerModel() {
	}

	/**
	 * Overloaded constructor to initialize customers attributes.
	 *
	 * @param customer_id	Unique identifier for the customer.
	 * @param first_name	The first name of the customer.
	 * @param last_name		The last name of the customer.
	 * @param email		The email address of the customer.
	 * @param phone		The phone of the customer.
	 * @param address		The address of the customer.
	 */
	public CustomerModel(Long customer_id, String first_name, String last_name, String email, String phone, String address) {
		this.customer_id = customer_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	// --- Getters and Setters ---

	/**
	 * @return The unique identifier of the customer.
	 */
	public Long getCustomer_id() {
		return customer_id;
	}

	/**
	 * Sets the unique identifier of the customer.
	 *
	 * @param customer_id	The unique identifier to set.
	 */
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}


	
	/**
	 * @return The first name of the customer.
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * Sets the first name of the customer.
	 *
	 * @param first_name	The first name to set.
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	
	/**
	 * @return The last name of the customer.
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * Sets the last name of the customer.
	 *
	 * @param last_name		The last name to set.
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return The email address of the customer.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the customer.
	 *
	 * @param email		The email address to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	


	/**
	 * @return The phone of the customer.
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * Sets the phone of the customer.
	 *
	 * @param phone		The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @return The address of the customer.
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * Sets the address of the customer.
	 *
	 * @param address		The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	public String getFullName() {
		
		return first_name + " " + last_name;
	}
	
	/**
	 * Returns a string representation of the CustomerModel.
	 *
	 */
	@Override
	public String toString() {
		return "(Id:" + customer_id + ") "  + first_name + " " + last_name ;
	}
}
