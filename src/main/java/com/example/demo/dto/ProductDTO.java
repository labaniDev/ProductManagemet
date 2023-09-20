package com.example.demo.dto;

import lombok.Data;
@Data
public class ProductDTO {
	
	private Long productid;
	private String title;
	private Integer type;
	private String created_at;
	private String updated_at;

}
