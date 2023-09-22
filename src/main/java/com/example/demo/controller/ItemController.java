package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ItemDTO;
import com.example.demo.service.ItemService;

@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "*")
public class ItemController {
	@Autowired
	ItemService itemService;
	
	@PostMapping("/additem")
	public ResponseEntity<ItemDTO> addItem(@RequestBody ItemDTO itemDTO) {
		itemService.addItem(itemDTO);
		return new ResponseEntity<ItemDTO>(HttpStatus.OK);
	}
	@GetMapping("/getAllItems")
	public List<ItemDTO> getAllItems(){
		return itemService.getAllItems();
	}
	@PutMapping("/updateItem")
	public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO){
		itemService.updateItem(itemDTO);
		return new ResponseEntity<ItemDTO>(HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteitem/{itemid}")
	public String deleteitem(@PathVariable("itemid") Long itemid) {
	itemService.deleteItemById(itemid);
	    return "Successfully Deleted";
	     }

}
