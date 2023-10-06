package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	
	 public static final Logger LOGGER = LoggerFactory.getLogger(BrandService.class);
	public void addBrand(BrandDTO brandDTO) {
		try {
			LOGGER.info("Add Brand");
		Brand brand=modelMapper.map(brandDTO, Brand.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now(); 
		  
		  brand.setCreated_at(dtf.format(now));
		  brand.setUpdated_at(dtf.format(now));
		  brandRepo.save(brand);
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		  
	}
	
	public List<BrandDTO> getAllBrand(){
		try {
			LOGGER.info("Get All Brands");
		List<Brand> brands=brandRepo.findAll();
		List<BrandDTO> brandDtoList=modelMapper.map(brands,new TypeToken<List<BrandDTO>>() {}.getType() );
		return brandDtoList;
	}catch(Exception ex) {
		ex.printStackTrace();
		LOGGER.error(ex.getMessage());
	}
		return null;
}
}
