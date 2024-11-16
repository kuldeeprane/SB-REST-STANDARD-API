package com.cjc.app.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.app.model.Product;
import com.cjc.app.service.ProductService;

@RestController
@RequestMapping(value="/api/v2")
public class ProductController {
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	
	@GetMapping(value="/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@GetMapping(value="/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id){
		Product product = productService.getProduct(id);
		if(product!=null)
			return ResponseEntity.ok(product);
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value="/products/{id}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Integer id){
		Boolean bool = productService.deleteProduct(id);
		if(bool)
			return ResponseEntity.ok(bool);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value="/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product){
		Product product2 = productService.updateProduct(id, product);
		if(product2 != null) {
			return ResponseEntity.ok(product2);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/products")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		Product product1 = productService.saveProduct(product);
		return ResponseEntity.ok(product1);
	}
	
	@PatchMapping(value = "/products/{id}")
	public ResponseEntity<Product> editProduct(@PathVariable("id") Integer id, @RequestBody Product product){
		Product editProduct = productService.editProduct(id, product);
		if(editProduct!=null) {
			return new ResponseEntity<Product>(editProduct, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	//search by category
	@GetMapping(value = "/products/bycategory/{category}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category){
		List<Product> list = productService.getProductByCategory(category);
		if(list != null)
			return new ResponseEntity<>(list, HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	//search by name
	//search by category and name
	@GetMapping("/products/bycategoryandname")
	public ResponseEntity<List<Product>> getProductByCategoryAndName(@RequestParam String category, String name){
		List<Product> list = productService.getProductByCategoryAndName(category, name);
		if(list != null)
			return new ResponseEntity<>(list, HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	//search between price
	//pagination
	@GetMapping(value = "/products/getpage")
	public ResponseEntity<List<Product>> getPages(@RequestParam Integer page,@RequestParam Integer size){
		List<Product> list = productService.getPages(page, size);
		if(list != null)
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/products/sort-price")
	public ResponseEntity<List<Product>> getProducts(){
		List<Product> list = productService.getSortByProductPrice();
		if(list != null)
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/products/sort-price-desc")
	public ResponseEntity<List<Product>> getProductsDesc(){
		List<Product> list = productService.getSortByProductPriceDesc();
		if(list != null)
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
