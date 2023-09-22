package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="category")
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long categoryid;
//private Long parentid;
private String title;
private String created_at ;
private String updated_at ;

@ManyToMany(mappedBy = "categories")
private List<Product> products = new ArrayList<Product>();

}
