package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	private Integer type;
	private String created_at;
	private String updated_at;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemid", referencedColumnName = "productid")
    private Item item;
	

}
