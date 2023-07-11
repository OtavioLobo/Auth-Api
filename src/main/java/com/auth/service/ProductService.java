package com.auth.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.auth.models.ProductModel;
import com.auth.repositories.ProductRepository;

@Service
public class ProductService {
	
	final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public boolean existsByName(String name) {
		return productRepository.existsByName(name);
	}

	public Object save(ProductModel productModel) {
		return productRepository.save(productModel);
	}

	public Page<ProductModel> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	

}
