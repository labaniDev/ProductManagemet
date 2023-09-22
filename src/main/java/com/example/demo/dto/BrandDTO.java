package com.example.demo.dto;

import lombok.Data;

@Data
public class BrandDTO {
	
	private Long brandid;
	private String title;
	private String summary;
	private String created_at;
	private String updated_at;

}
