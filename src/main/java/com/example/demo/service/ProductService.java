package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepo;

@Service
public class ProductService {
	@Autowired
	ProductRepo productRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	CategoryRepo categoryRepo;
	
	
	public void addProduct(ProductDTO productDTO) {
		
		Product product=modelMapper.map(productDTO, Product.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now(); 
		  
		  product.setCreated_at(dtf.format(now));
		  product.setUpdated_at(dtf.format(now));
		  
		  Set<Category> categories=new HashSet<>();
		  if(productDTO.getCategoryid()!=null) {
		  
		  Category categoryList = categoryRepo.findById(productDTO.getCategoryid())
					.orElseThrow(() -> new RuntimeException("Error: Category is not found."));
		  categories.add(categoryList);
			  }
		  product.setCategories(categories);
		  productRepo.save(product);
		  }
		  
		  
	
	
	public List<ProductDTO> getAllProduct(){
		List<Product> products=productRepo.findAll();
		List<ProductDTO> productDTOList= modelMapper.map(products,new TypeToken<List<ProductDTO>>() {}.getType() );
		return productDTOList;
	}
	
  public ProductDTO  getProductByid(Long productid) {
	  Optional<Product> productOptional=productRepo.findById(productid);
	  
	 if(productOptional.isPresent()) {
		 Product product=productOptional.get();
		 ProductDTO productdto= modelMapper.map(product,ProductDTO.class);
		return productdto;
	 }
	return null;
  }
  
  public Product updateProduct(ProductDTO productDTO) {
	  Optional<Product> productOptional=productRepo.findById(productDTO.getProductid());
	  
	  if(productOptional.isPresent()) {
		  Product newProduct=productOptional.get();
		  newProduct.setTitle(productDTO.getTitle());
		  newProduct.setType(productDTO.getType());
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now(); 
		  
		  newProduct.setCreated_at(dtf.format(now));
		  newProduct.setUpdated_at(dtf.format(now));
		  return productRepo.save(newProduct);
	  }
	  return null;
  }
  
 public int deleteProduct(Long productid) {
	 Optional<Product> product=productRepo.findById(productid);
	 if(product.isPresent()) {
		 productRepo.deleteById(productid);
		 return 0;
	 }
	 return 1;
 }
  
  
 

}
