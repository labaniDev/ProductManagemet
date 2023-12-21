package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BrandDTO;
import com.example.demo.entity.Brand;
import com.example.demo.entity.Status;
import com.example.demo.repository.BrandRepo;

@Service
public class BrandService {
	@Autowired
	BrandRepo brandRepo;
	@Autowired
	ModelMapper modelMapper;

	
	 public static final Logger LOGGER = LoggerFactory.getLogger(BrandService.class);
	 @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void addBrand(BrandDTO brandDTO) {
		try {
			LOGGER.debug("Inside AddBrand::"+brandDTO.toString());
		Brand brand=modelMapper.map(brandDTO, Brand.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now(); 
		  
		  brand.setCreated_at(dtf.format(now));
		  brand.setUpdated_at(dtf.format(now));
		  brand.setStatus(Status.active);
		  brandRepo.save(brand);
		  LOGGER.debug("Brand Added Successfully");
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception in add Category::"+ex.getMessage());
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
	
	public String iactiveBrandByid(Long id) {
		try {
			LOGGER.info("Inactive Brand By id");
			Optional<Brand> brandOptional=brandRepo.findById(id);
			if(brandOptional.isPresent()) {
				Brand brand=brandOptional.get();
				brand.setStatus(Status.inactive);
				brandRepo.save(brand);
				return "Brand Successfully marked as inactive";
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}return "Brand already marked as inactive";
	}
	
}
