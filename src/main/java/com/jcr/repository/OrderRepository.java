package com.jcr.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jcr.model.CustomerModel;
import com.jcr.model.OrderModel;
import com.jcr.model.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<OrderModel> findAll() {
		String sql = "SELECT * FROM orders ORDER BY order_date DESC";
		return jdbcTemplate.query(sql, new OrderRowMapper());
	}

	public OrderModel save(OrderModel order) {
		String sql = "INSERT INTO orders (customer_id, product_id, quantity, order_date) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, order.getCustomer().getCustomer_id(),
				order.getProduct().getProduct_id(), order.getQuantity(),
				Timestamp.valueOf(order.getOrder_date()));
		return order;
	}

	public OrderModel update(OrderModel order) {
		String sql = "UPDATE orders SET customer_id=?, product_id=?, quantity=?, order_date=? WHERE order_id=?";
		jdbcTemplate.update(sql, order.getCustomer().getCustomer_id(),
				order.getProduct().getProduct_id(), order.getQuantity(),
				Timestamp.valueOf(order.getOrder_date()), order.getOrder_id());
		return order;
	}

	public int delete(Long order_id) {
		String sql = "DELETE FROM orders WHERE order_id=?";
		return jdbcTemplate.update(sql, order_id);
	}

	public OrderModel findById(Long order_id) {
		String sql = "SELECT * FROM orders WHERE order_id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new OrderRowMapper(),order_id);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return null;
		}
	}

	private class OrderRowMapper implements RowMapper<OrderModel> {
		@Override
		public OrderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			CustomerModel customer = new CustomerModel();
			customer.setCustomer_id(rs.getLong("customer_id"));

			ProductModel product = new ProductModel();
			product.setProduct_id(rs.getLong("product_id"));

			return new OrderModel(rs.getLong("order_id"), customer, product,
					rs.getInt("quantity"), rs.getTimestamp("order_date").toLocalDateTime());
		}
	}

	public List<OrderModel> findFiveMostRecentOrders() {
		String sql = "SELECT * FROM orders ORDER BY order_date DESC LIMIT 5";
		return jdbcTemplate.query(sql, new OrderRowMapper());
	}

}
