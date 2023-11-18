package com.jcr.repository;

import com.jcr.model.CustomerModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Repository class for performing CRUD operations on {@link CustomerModel}
 * entities in the database.
 * 
 * @version 1.0
 */
@Repository
public class CustomerRepository {

	/**
	 * Automatically injects the JdbcTemplate bean to interact with the database.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Retrieves all customers from the database.
	 *
	 * @return a list of all customers.
	 */
	public List<CustomerModel> findAll() {
		String sql = "SELECT * FROM customers";
		return jdbcTemplate.query(sql, new CustomerRowMapper());
	}

	/**
	 * Saves a new customer to the database.
	 *
	 * @param the customer object to be saved.
	 * @return the saved customer object.
	 */
	public CustomerModel save(CustomerModel customer) {
		String sql = "INSERT INTO customers (first_name, last_name, email, phone, address) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, customer.getFirst_name(), customer.getLast_name(), customer.getEmail(), customer.getPhone(), customer.getAddress());
		return customer;
	}

	/**
	 * Updates the details of an existing customer in the database.
	 *
	 * @param the customer object with updated details.
	 * @return the updated customer object.
	 */
	public CustomerModel update(CustomerModel customer) {
		String sql = "UPDATE customers SET first_name=?, last_name=?, email=?, phone=?, address=? WHERE customer_id=?";
		jdbcTemplate.update(sql, customer.getFirst_name(), customer.getLast_name(), customer.getEmail(),customer.getPhone(), customer.getAddress(), customer.getCustomer_id());
		return customer;
	}

	/**
	 * Deletes a customer from the database.
	 *
	 * @param customer_id the ID of the customer to be deleted.
	 * @return the number of rows affected.
	 */
	public int delete(Long customer_id) {
		String sql = "DELETE FROM customers WHERE customer_id=?";
		return jdbcTemplate.update(sql, customer_id);
	}

	/**
	 * Retrieves a specific customer from the database by its ID.
	 *
	 * @param customer_id the ID of the customer.
	 * @return the customer object if found, null otherwise.
	 */
	public CustomerModel findById(Long customer_id) {
		String sql = "SELECT * FROM customers WHERE customer_id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), customer_id);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * Maps a result set row to a customer model object.
	 */
	private class CustomerRowMapper implements RowMapper<CustomerModel> {
		@Override
		public CustomerModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new CustomerModel(rs.getLong("customer_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("phone"), rs.getString("address"));
		}
	}

	/**
	 * Retrieves the last five customers added to the database.
	 *
	 * @return a list of the last five customers.
	 */
	public List<CustomerModel> findLastFiveCustomers() {
		String sql = "SELECT * FROM customers ORDER BY customer_id DESC LIMIT 5";
		return jdbcTemplate.query(sql, new CustomerRowMapper());
	}

}
