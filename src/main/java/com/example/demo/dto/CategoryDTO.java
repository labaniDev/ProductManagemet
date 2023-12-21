package com.example.demo.dto;

import java.util.Set;

import com.example.demo.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
	
	private Long id;
	//private Long parentid;
	private String title;
	private String description;
	private String created_at ;
	private String updated_at ;
	private Status status;
	@JsonIgnore
	private Set<ProductDTO> products;
	

}
