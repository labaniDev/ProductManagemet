package com.example.demo.dto;

import java.util.Set;

import com.example.demo.entity.Status;

import lombok.Data;
@Data
public class ProductDTO {
	
	private Long id;
	private String title;
	private String description;
	private String created_at;
	private String updated_at;
	private Status status;
	private Set<ItemDTO> items;


}
