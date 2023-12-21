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

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Status;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepo;
@Service
public class CategoryService {

@Autowired
CategoryRepo categoryRepo;
@Autowired
ModelMapper modelMapper;
@Autowired
ProductRepo productRepo;

    public static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)   
public void addCategory(CategoryDTO categorydto){
	try {
		LOGGER.debug("Inside addProduct::"+categorydto.toString());
	Category category = modelMapper.map(categorydto,Category.class);
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  LocalDateTime now = LocalDateTime.now();  
	 category.setCreated_at(dtf.format(now));
	 category.setUpdated_at(dtf.format(now));
	 category.setStatus(Status.active);
	 categoryRepo.save(category);
	 LOGGER.debug("Category Added Successfully");
     }catch(Exception ex) {
    	 ex.printStackTrace();
			LOGGER.error("Exception in add Category::"+ex.getMessage());
     }
     }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED) 
public String updatecategory(CategoryDTO categorydto) {
    	try {
    		LOGGER.debug("Inside updateCategory::"+categorydto.toString());
	Optional <Category> categoryoptional = categoryRepo.findById(categorydto.getId());
	if(categoryoptional.isPresent()) {
		Category category=categoryoptional.get();
		category.setTitle(categorydto.getTitle());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now();  
		 category.setCreated_at(dtf.format(now));
		 category.setUpdated_at(dtf.format(now));
		 categoryRepo.save(category);
		 return "update";
	  }}catch(Exception ex) {
	    	 ex.printStackTrace();
				LOGGER.error("Exception in add Category::"+ex.getMessage());
	     }
    	return "not update";
   } 

		
 public String inactiveCategoryById(Long categoryid) {
	 
     LOGGER.info("inactive Category by Id");
     try {
	 Optional<Category>categoryList=categoryRepo.findById(categoryid);
	 if(categoryList.isPresent()) {
		 Category category=categoryList.get();
		 category.setStatus(Status.inactive);
		 categoryRepo.save(category);
	 return "Category Succesfully marked as inactive";
	 }
     } catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return "Category is already inactive";
	}

 
		

//public void removeCategoryFromProduct(Long categoryid, Long productid) {
//    Product product = productRepo.findById(productid)
//            .orElseThrow();
//
//    Category category = product.getCategories().stream()
//            .filter(r -> r.getCategoryid().equals(categoryid))
//            .findFirst()
//            .orElseThrow();
//
//    product.getCategories().remove(category);
//    productRepo.save(product);
//}

public  List<CategoryDTO> getAllCategories(){
	try {
		LOGGER.info("Get All Categories");
	List<Category> categoryList=categoryRepo.findAll();	
	List<CategoryDTO> categorydtoList= modelMapper.map(categoryList,new TypeToken<List<CategoryDTO>>() {}.getType() );
     return categorydtoList;
     }catch(Exception ex) {
    	 ex.printStackTrace();
			LOGGER.error(ex.getMessage());
     }
	return null;
}
public CategoryDTO getCategoriesById(Long categoryid) {
	try {
		LOGGER.info("Get Category By id");
	 Optional<Category> category = categoryRepo.findById(categoryid);
	     if(category.isPresent()) {
	    	 CategoryDTO categorydto= modelMapper.map(category,CategoryDTO.class);	 
		  return categorydto;
	     }
	 return null;
    }catch(Exception ex) {
    	 ex.printStackTrace();
			LOGGER.error(ex.getMessage());
    }
	return null;
}
}
