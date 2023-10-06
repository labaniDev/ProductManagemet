package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private float mrp;
private float discount;
private float price;
private String created_at;
private String updated_at;

@OneToOne
@JoinColumn(name="productid",referencedColumnName="id")
private Product product;


@OneToOne
@JoinColumn(name="brandid",referencedColumnName="id")
private Brand brand;


}
