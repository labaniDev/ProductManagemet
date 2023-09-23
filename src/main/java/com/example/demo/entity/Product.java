package com.example.demo.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productid;
	private String title;
	private String description;
	private Integer type;
	private String created_at;
	private String updated_at;
	
//	@OneToOne(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
//   private Item  item;
	
//	@OneToOne(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
//	private Productdetails productdetails;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="product_category", joinColumns = @JoinColumn(name = "productid"), inverseJoinColumns = @JoinColumn(name = "categoryid"))
	private Set<Category> categories = new HashSet<>();


}
