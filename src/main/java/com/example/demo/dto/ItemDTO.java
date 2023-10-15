package com.example.demo.dto;

import com.example.demo.entity.Status;

import lombok.Data;

@Data
public class ItemDTO {
    
	
	private Long id;
	private String title;
	private float mrp;
	private float discount;
	private float price;
	private Status status;
	private String created_at;
	private String updated_at;
	private BrandDTO brand;
	


}
