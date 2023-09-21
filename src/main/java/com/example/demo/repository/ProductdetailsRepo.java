package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Productdetails;

@Repository
public interface ProductdetailsRepo extends CrudRepository<Productdetails,Long>,JpaRepository<Productdetails,Long> {

}
