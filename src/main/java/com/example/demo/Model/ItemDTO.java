package com.example.demo.Model;

import lombok.Data;

@Data
public class ItemDTO {
	private Long itemid;
	private float mrp;
	private float discount;
	private float price;
	private String created_at;
	private String update_at;	 
}
