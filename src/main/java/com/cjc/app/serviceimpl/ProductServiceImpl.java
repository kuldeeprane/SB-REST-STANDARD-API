package com.cjc.app.serviceimpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cjc.app.model.Product;
import com.cjc.app.repository.ProductRepository;
import com.cjc.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Integer id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).get();
	}

	@Override
	public Boolean deleteProduct(Integer id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return true;
		}

		return false;
	}

	@Override
	public Product updateProduct(Integer id, Product product) {
		if(productRepository.existsById(id)) {
			product.setProductId(id);
			return productRepository.save(product);
		}
		return null;
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product editProduct(Integer id, Product product) {
		if(productRepository.existsById(id)) {
			Product extPro = productRepository.findById(id).get();
			
			if(product.getProductName()!=null)
				extPro.setProductName(product.getProductName());
			
			if(product.getCompanyName()!=null)
				extPro.setCompanyName(product.getCompanyName());
			
			if(product.getProductColor()!=null)
				extPro.setProductColor(product.getProductColor());
			
			if(product.getProductPrice()!=null)
				extPro.setProductPrice(product.getProductPrice());
			
			if(product.getProductCategory()!=null)
				extPro.setProductCategory(product.getProductCategory());
			
			return productRepository.save(extPro); //existing product
		}
		return null;
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		return productRepository.findAllByProductCategory(category);
	}

	@Override
	public List<Product> getProductByCategoryAndName(String category, String name) {
		return productRepository.findAllByProductCategoryAndProductName(category, name);
	}

	@Override
	public List<Product> getPages(Integer page, Integer size) {
		PageRequest pageReq = PageRequest.of(page, size);
		Page<Product> content = productRepository.findAll(pageReq);
		List<Product> products = content.getContent();
		return products;
	}

	@Override
	public List<Product> getSortByProductPrice() {
		// TODO Auto-generated method stub
		return productRepository.findAllByOrderByProductPriceAsc();
	}

	@Override
	public List<Product> getSortByProductPriceDesc() {
		// TODO Auto-generated method stub
		return productRepository.findAllByOrderByProductPriceDesc();
	}
}
