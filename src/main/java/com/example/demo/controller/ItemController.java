package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ItemDTO;
import com.example.demo.service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {
	@Autowired
	ItemService itemService;
	
	@GetMapping("/getAllItems")
	public List<ItemDTO> getAllItems(){
		return itemService.getAllItems();
	}
	@PutMapping("/updateItem")
	public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO){
		itemService.updateItem(itemDTO);
		return new ResponseEntity<ItemDTO>(HttpStatus.OK);
	}

}
