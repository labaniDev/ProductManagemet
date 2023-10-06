package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;
import java.util.List;
import java.util.Optional;
import java.util.Set;



@Repository
public interface CategoryRepo extends CrudRepository<Category,Long>,JpaRepository<Category,Long>{
	
	Optional<Category>  findById(Long id);
}
