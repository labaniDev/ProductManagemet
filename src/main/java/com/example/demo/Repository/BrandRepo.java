package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Brand;
@Repository
public interface BrandRepo extends CrudRepository<Brand,Long>,JpaRepository<Brand,Long> {

}
