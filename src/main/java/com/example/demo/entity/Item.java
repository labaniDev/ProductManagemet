package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="item")
public class Item {
	private Long itemid;
	private Long brandid;
	private float mrp;
	private float discount;
	private float price;
	private String created_at;
	private String updated_at;
	
	
	@OneToOne(mappedBy="Product")
	private Product product;
	

}
