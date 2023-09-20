package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.ItemDTO;
import com.example.demo.Service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {

@Autowired
ItemService itemService;
	
@PostMapping("/additem")	
public ResponseEntity<String>createitem(@RequestBody ItemDTO itemdto){
	itemService.createitem(itemdto);
	return new ResponseEntity<String>(HttpStatus.CREATED);
     }
}
