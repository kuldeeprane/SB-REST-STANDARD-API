package com.cjc.app.service;

import java.util.List;

import com.cjc.app.model.Product;

public interface ProductService {

	List<Product> getAllProducts();

	Product getProduct(Integer id);

	Boolean deleteProduct(Integer id);

	Product updateProduct(Integer id, Product product);

	Product saveProduct(Product product);

	Product editProduct(Integer id, Product product);

	List<Product> getProductByCategory(String category);

	List<Product> getProductByCategoryAndName(String category, String name);

	List<Product> getPages(Integer page, Integer size);

	List<Product> getSortByProductPrice();

	List<Product> getSortByProductPriceDesc();

}
