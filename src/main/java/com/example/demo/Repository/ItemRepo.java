package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Item;
@Repository
public interface ItemRepo extends CrudRepository<Item,Long>,JpaRepository<Item,Long> {

	
}
