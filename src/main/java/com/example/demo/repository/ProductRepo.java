package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends CrudRepository<Product,Long>,JpaRepository<Product,Long> {
	List<Product>  findByStatus(Status status);
	Product findByTitleAndStatus(String title, Status status);
	Optional<Product>  findById(Long id);
	

}
