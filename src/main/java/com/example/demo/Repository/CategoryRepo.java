package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;


@Repository
public interface CategoryRepo extends CrudRepository<Category,Integer>,JpaRepository<Category,Integer>{

}
