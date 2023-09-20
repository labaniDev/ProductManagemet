package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.Category;

@Repository
public interface CategoryRepo extends CrudRepository<Category,Integer>,JpaRepository<Category,Integer>{

}
