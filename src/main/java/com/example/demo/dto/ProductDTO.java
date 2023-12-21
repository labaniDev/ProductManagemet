package com.example.demo.dto;

import java.io.Serializable;
import java.util.Set;

import com.example.demo.entity.Status;

import lombok.Data;
@Data
public class ProductDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String description;
	private String created_at;
	private String updated_at;
	private Status status;
    //private Set<CategoryDTO> categories;
	private Set<ItemDTO> items;


}
