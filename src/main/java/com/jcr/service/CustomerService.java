package com.jcr.service;

import com.jcr.model.CustomerModel;
import com.jcr.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing {@link CustomerModel} entities. This
 * class interacts with the {@link CustomerRepository} to perform CRUD operations
 * on customers.
 * 
 * @version 1.0
 */
@Service
public class CustomerService {

	/**
	 * Repository instance for customer operations.
	 */
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Fetch all customer entities from the database.
	 *
	 * @return List of all customers.
	 */
	public List<CustomerModel> getAllCustomers() {
		List<CustomerModel> customers = new ArrayList<>();
		// Fetch customers from repository and add to the customers list
		customerRepository.findAll().forEach(customers::add);
		return customers;
	}

	/**
	 * Add a new customer to the database.
	 *
	 * @param the customer object to be added.
	 * @return the saved customer object.
	 */
	public CustomerModel addCustomer(CustomerModel customer) {
		return customerRepository.save(customer);
	}

	/**
	 * Retrieve a customer by its unique ID.
	 *
	 * @param customer_id the ID of the desired customer.
	 * @return the found customer object, or null if not found.
	 */
	public CustomerModel getCustomerById(Long customer_id) {
		return customerRepository.findById(customer_id);
	}

	/**
	 * Update details of an existing customer in the database.
	 *
	 * @param customer the customer object with updated details.
	 * @return the updated customer object.
	 */
	public CustomerModel editCustomer(CustomerModel customer) {
		return customerRepository.update(customer);
	}

	/**
	 * Delete a customer by its unique ID from the database.
	 *
	 * @param customer_id the ID of the customer to be deleted.
	 * @return the number of rows affected by the delete operation.
	 */
	public int deleteCustomer(Long customer_id) {
		return customerRepository.delete(customer_id);
	}

	/**
	 * Retrieve the last five customers added to the database.
	 *
	 * @return a list of the last five customers.
	 */
	public List<CustomerModel> getLastFiveCustomers() {
		return customerRepository.findLastFiveCustomers();
	}
}
