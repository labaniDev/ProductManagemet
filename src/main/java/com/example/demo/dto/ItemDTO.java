package com.example.demo.dto;

import lombok.Data;

@Data
public class ItemDTO {
    
	
	private Long itemid;
	private Long brandid;
	private float mrp;
	private float discount;
	private float price;
	private String created_at;
	private String updated_at;
	private Long productid;

}
