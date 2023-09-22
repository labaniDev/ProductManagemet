package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BrandDTO;
import com.example.demo.service.BrandService;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin(origins = "*")
public class BrandController {
	@Autowired
	BrandService brandService;
	
	@PostMapping("/addBrand")
	public ResponseEntity<BrandDTO> addBrand(@RequestBody BrandDTO brandDTO) {
		brandService.addBrand(brandDTO);
		return new ResponseEntity<BrandDTO>(HttpStatus.CREATED);
	}
	@GetMapping("/getAllBrand")
	public List<BrandDTO> getAllBrand(){
		return brandService.getAllBrand();
	}

}
