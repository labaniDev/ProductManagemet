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
// to update category
//@PutMapping("/updatecategory")
//public ResponseEntity<String>updatecategory(@RequestBody CategoryDTO categorydto){
//	categoryService.updatecategory(categorydto);
//	return new ResponseEntity<String>(HttpStatus.OK);
//      }
//to delete category
//@DeleteMapping("/deletecategory/{categoryid}/{productid}")
//public String deleteCategory(@PathVariable("categoryid") Long categoryid,@PathVariable("productid") Long productid) {
//	categoryService.removeCategoryFromProduct(categoryid, productid);
//    return "Successfully Deleted";
//     }
//to get all category
@GetMapping("/getallcategory")
private List<CategoryDTO>getAllCategories(){
  return categoryService.getAllCategories();
     }
//to get category by id
@GetMapping("/category/{categoryid}")
public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("categoryid") Long categoryid) {
	CategoryDTO categorydto= categoryService.getCategoriesById(categoryid);
	 return new ResponseEntity<CategoryDTO>(categorydto,HttpStatus.OK);
     }
}