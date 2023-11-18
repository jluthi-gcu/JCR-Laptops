package com.jcr.service;

import com.jcr.model.ProductModel;
import com.jcr.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing {@link ProductModel} entities. This
 * class interacts with the {@link ProductRepository} to perform CRUD operations
 * on products.
 * 
 * @version 1.0
 */
@Service
public class ProductService {

	/**
	 * Repository instance for product operations.
	 */
	@Autowired
	private ProductRepository productRepository;

	/**
	 * Fetch all product entities from the database.
	 *
	 * @return List of all products.
	 */
	public List<ProductModel> getAllProducts() {
		List<ProductModel> products = new ArrayList<>();
		// Fetch products from repository and add to the products list
		productRepository.findAll().forEach(products::add);
		return products;
	}

	/**
	 * Add a new product to the database.
	 *
	 * @param the product object to be added.
	 * @return the saved product object.
	 */
	public ProductModel addProduct(ProductModel product) {
		return productRepository.save(product);
	}

	/**
	 * Retrieve a product by its unique ID.
	 *
	 * @param product_id the ID of the desired product.
	 * @return the found product object, or null if not found.
	 */
	public ProductModel getProductById(Long product_id) {
		return productRepository.findById(product_id);
	}

	/**
	 * Update details of an existing product in the database.
	 *
	 * @param product the product object with updated details.
	 * @return the updated product object.
	 */
	public ProductModel editProduct(ProductModel product) {
		return productRepository.update(product);
	}

	/**
	 * Delete a product by its unique ID from the database.
	 *
	 * @param product_id the ID of the product to be deleted.
	 * @return the number of rows affected by the delete operation.
	 */
	public int deleteProduct(Long product_id) {
		return productRepository.delete(product_id);
	}

	/**
	 * Retrieve the last five products added to the database.
	 *
	 * @return a list of the last five products.
	 */
	public List<ProductModel> getLastFiveProducts() {
		return productRepository.findLastFiveProducts();
	}
}
