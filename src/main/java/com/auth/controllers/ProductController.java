package com.auth.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dtos.ProductDto;
import com.auth.models.ProductModel;
import com.auth.service.ProductService;

@RestController
@RequestMapping("/produtos")
public class ProductController {

	final ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<Page<ProductModel>> getAllProducts(@PageableDefault(page = 0, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(pageable));
		
	}
	
	@PostMapping
	public ResponseEntity<Object> saveProduct(@Valid ProductDto productDto) {
		
		if(productService.existsByName(productDto.getName())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: Produto já cadastrado!");
		}
		
		var productModel = new ProductModel();	
		BeanUtils.copyProperties(productDto, productModel);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
	}
	
	
}
