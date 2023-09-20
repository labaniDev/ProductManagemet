package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.CategoryDTO;
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
@DeleteMapping("/deleteitem/{itemid}")
public String deleteitem(@PathVariable("itemid") int itemid) {
	itemService.deleteItemById(itemid);
    return "Successfully Deleted";
     }

@GetMapping("/getallitem")
private List<ItemDTO>getAllItems(){
  return itemService.getAllitems();
     }
@GetMapping("/item/{itemid}")
public ResponseEntity<ItemDTO> getItemById(@PathVariable long itemid) {
	ItemDTO itemdto= itemService.getItemsById(itemid);
	 return new ResponseEntity<ItemDTO>(itemdto,HttpStatus.OK);
     }
}
