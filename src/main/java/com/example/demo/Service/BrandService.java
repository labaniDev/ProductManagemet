package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BrandDTO;
import com.example.demo.entity.Brand;
import com.example.demo.repository.BrandRepo;

@Service
public class BrandService {
	@Autowired
	BrandRepo brandRepo;
	@Autowired
	ModelMapper modelMapper;

	
	
	public void addBrand(BrandDTO brandDTO) {
		Brand brand=modelMapper.map(brandDTO, Brand.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now(); 
		  
		  brand.setCreated_at(dtf.format(now));
		  brand.setUpdated_at(dtf.format(now));
		  brandRepo.save(brand);
		  
	}
	
	public List<BrandDTO> getAllBrand(){
		List<Brand> brands=brandRepo.findAll();
		List<BrandDTO> brandDtoList=modelMapper.map(brands,new TypeToken<List<BrandDTO>>() {}.getType() );
		return brandDtoList;
	}
}
