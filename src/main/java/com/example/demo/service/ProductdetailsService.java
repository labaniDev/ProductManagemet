package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductdetailsDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.Productdetails;
import com.example.demo.repository.ProductdetailsRepo;

@Service
public class ProductdetailsService {
	
	@Autowired
	ProductdetailsRepo productdetailsRepo;
	@Autowired
	ModelMapper modelMapper;
	
	public void addProductdetails(ProductdetailsDTO productdetailsDTO) {
		Productdetails product=modelMapper.map(productdetailsDTO, Productdetails.class);
	}

}
