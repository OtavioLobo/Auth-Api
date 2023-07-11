package com.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long>{

	public boolean existsByName(String name);

}
