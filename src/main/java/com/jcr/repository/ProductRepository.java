package com.jcr.repository;

import com.jcr.model.ProductModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Repository class for performing CRUD operations on {@link ProductModel}
 * entities in the database.
 * 
 * @version 1.0
 */
@Repository
public class ProductRepository {

	/**
	 * Automatically injects the JdbcTemplate bean to interact with the database.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Retrieves all products from the database.
	 *
	 * @return a list of all products.
	 */
	public List<ProductModel> findAll() {
		String sql = "SELECT * FROM products";
		return jdbcTemplate.query(sql, new ProductRowMapper());
	}

	/**
	 * Saves a new product to the database.
	 *
	 * @param the product object to be saved.
	 * @return the saved product object.
	 */
	public ProductModel save(ProductModel product) {
		String sql = "INSERT INTO products (name, description, price, quantity) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
		return product;
	}

	/**
	 * Updates the products of an existing product in the database.
	 *
	 * @param the product object with updated details.
	 * @return the updated product object.
	 */
	public ProductModel update(ProductModel product) {
		String sql = "UPDATE products SET name=?, description=?, price=?, quantity=? WHERE product_id=?";
		jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getProduct_id());
		return product;
	}

	/**
	 * Deletes a product from the database.
	 *
	 * @param product_id the ID of the product to be deleted.
	 * @return the number of rows affected.
	 */
	public int delete(Long product_id) {
		String sql = "DELETE FROM products WHERE product_id=?";
		return jdbcTemplate.update(sql, product_id);
	}

	/**
	 * Retrieves a specific product from the database by its ID.
	 *
	 * @param product_id the ID of the product.
	 * @return the product object if found, null otherwise.
	 */
	public ProductModel findById(Long product_id) {
		String sql = "SELECT * FROM products WHERE product_id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), product_id);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * Maps a result set row to a product model object.
	 */
	private class ProductRowMapper implements RowMapper<ProductModel> {
		@Override
		public ProductModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new ProductModel(rs.getLong("product_id"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getInt("quantity"));
		}
	}

	/**
	 * Retrieves the last five products added to the database.
	 *
	 * @return a list of the last five products.
	 */
	public List<ProductModel> findLastFiveProducts() {
		String sql = "SELECT * FROM products ORDER BY product_id DESC LIMIT 5";
		return jdbcTemplate.query(sql, new ProductRowMapper());
	}

}
