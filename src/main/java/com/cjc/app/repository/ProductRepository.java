package com.cjc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.app.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findAllByProductCategory(String category);
	List<Product> findAllByOrderByProductPriceAsc();
	List<Product> findAllByOrderByProductPriceDesc();
	List<Product> findAllByProductCategoryAndProductName(String productCategory, String productname);
	List<Product> findAllByProductPriceBetween(Double minProductPrice, Double maxProductPrice);
}
