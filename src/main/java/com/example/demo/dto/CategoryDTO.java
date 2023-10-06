package com.example.demo.dto;

import java.util.Set;

import com.example.demo.entity.Product;

import lombok.Data;

@Data
public class CategoryDTO {
	
	private Long id;
	//private Long parentid;
	private String title;
	private String created_at ;
	private String updated_at ;
	//private Set<CategoryDTO> categories
	Set<ProductDTO> products;
	

}
