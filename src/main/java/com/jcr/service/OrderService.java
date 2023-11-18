package com.jcr.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcr.model.OrderModel;
import com.jcr.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * Fetch all orders from the repository.
	 * 
	 * @return List of all orders.
	 */
	public List<OrderModel> getAllOrders() {
		List<OrderModel> orders = new ArrayList<>();
		orderRepository.findAll().forEach(orders::add);
		return orders;
	}


	public OrderModel addWorkout(OrderModel order) {
		return orderRepository.save(order);
	}

	
	public OrderModel getOrderById(Long order_id) {
		return orderRepository.findById(order_id);
	}

	
	public OrderModel editOrder(OrderModel order) {
		return orderRepository.update(order);
	}

	
	public int deleteOrder(Long order_id) {
		return orderRepository.delete(order_id);
	}

	// Fetch the next five scheduled orders
	public List<OrderModel> getFiveMostRecentOrders() {
		return orderRepository.findFiveMostRecentOrders();
	}
}
