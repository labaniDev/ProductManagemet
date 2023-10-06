package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductdetailsDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.Productdetails;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.ProductdetailsRepo;

@Service
public class ProductdetailsService {
	
	@Autowired
	ProductdetailsRepo productdetailsRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ProductRepo productRepo;
	 public static final Logger LOGGER = LoggerFactory.getLogger(ProductdetailsService.class);
	public void addProductdetails(ProductdetailsDTO productdetailsDTO) {
		try {
			LOGGER.info("Add ProductDetails");
		Optional<Product> existingProduct=productRepo.findById(productdetailsDTO.getProductid());
		if(existingProduct.isPresent()) {
			Product products=existingProduct.get();
			Productdetails productDetails=new Productdetails();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now(); 
		  
		  productDetails.setCreated_at(dtf.format(now));
		  productDetails.setUpdated_at(dtf.format(now));
		  //productDetails.setProduct(products);
		  
		  productdetailsRepo.save(productDetails);
	}

}catch(Exception ex) {
	ex.printStackTrace();
	LOGGER.error(ex.getMessage());
}
}
}
