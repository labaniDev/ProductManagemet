package com.example.demo.dto;

import com.example.demo.entity.Status;

import lombok.Data;

@Data
public class BrandDTO {
	
	private Long id;
	private String title;
	private String summary;
	private Status status;
	private String created_at;
	private String updated_at;

}
