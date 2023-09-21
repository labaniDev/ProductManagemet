package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Item;


public interface ItemRepo extends CrudRepository<Item,Long>,JpaRepository<Item,Long> {

}
