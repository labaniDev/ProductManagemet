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

import com.example.demo.dto.CategoryDTO;
import com.example.demo.service.CategoryService;


@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {
	
@Autowired
CategoryService categoryService;

//to add category
@PostMapping("/addcategory")	
public ResponseEntity<String>createcategory(@RequestBody CategoryDTO categorydto){
	categoryService.createproduct(categorydto);
	return new ResponseEntity<String>(HttpStatus.CREATED);
      }
 //to update category
@PutMapping("/updatecategory")
public ResponseEntity<String>updatecategory(@RequestBody CategoryDTO categorydto){
	categoryService.updatecategory(categorydto);
	return new ResponseEntity<String>(HttpStatus.OK);
      }
//to inactive category
@DeleteMapping("/inactiveCategory/{id}")
public String inactiveCategory(@PathVariable("id") Long id) {
	categoryService.inactiveCategoryById(id);
    return "Category Successfully marked as inactive";
     }
//to get all category
@GetMapping("/getallcategory")
private List<CategoryDTO>getAllCategories(){
  return categoryService.getAllCategories();
     }
//to get category by id
//@GetMapping("/category/{categoryid}")
//public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("categoryid") Long categoryid) {
//	CategoryDTO categorydto= categoryService.getCategoriesById(categoryid);
//	 return new ResponseEntity<CategoryDTO>(categorydto,HttpStatus.OK);
//     }
}