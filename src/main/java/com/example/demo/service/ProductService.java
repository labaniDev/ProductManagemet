package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Status;
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

//	PropertyMap<ProductDTO, Product> skipModifiedFieldsMap = new PropertyMap<ProductDTO, Product>() {
//		protected void configure() {
//			skip().setCategories(null);
//		}
//	};

	public static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	public void addProduct(CategoryDTO categoryDTO) {
		try {
			LOGGER.info("Adding product" + categoryDTO);

			Optional<Category> category=categoryRepo.findById(categoryDTO.getId());
			
			if(category.isPresent()) {
				
				//modelMapper.addMappings(skipModifiedFieldsMap);
				List<Product> products =  modelMapper.map(categoryDTO.getProducts(),new TypeToken<List<Product>>()  {}.getType());
				
				products.stream().forEach(prd->{
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();

					prd.setCreated_at(dtf.format(now));
					prd.setUpdated_at(dtf.format(now));
					prd.setStatus(Status.active);
				});
				
				category.get().getProducts().addAll(products);
				categoryRepo.save(category.get());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}

	}

	public List<ProductDTO> getAllProduct(Long id) {
		try {
			LOGGER.info("Get All Product");
			Optional<Category> productCategory=categoryRepo.findById(id);
			if(productCategory.isPresent()) {
				Category category=productCategory.get();
				Set<Product> products=category.getProducts();
				List<ProductDTO> productdtoList=modelMapper.map(products,new TypeToken<List<ProductDTO>>() {}.getType() );
				return productdtoList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return null;
	}



	public List<ProductDTO> getActiveProducts(Long id) {
		try {
			LOGGER.info("Get Active Products");
			Optional<Category> categoryOptional=categoryRepo.findById(id);
			if(categoryOptional.isPresent()) {
				Category category=categoryOptional.get();
				Set<Product> products = category.getProducts();
				List<ProductDTO> productDTOList =  modelMapper.map(products,new TypeToken<List<ProductDTO>>()  {}.getType());
				return productDTOList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return null;
	}

	public ProductDTO getProductByProductName(String title) {
		try {
			LOGGER.info("Get Product By Product Name");
			Product product = productRepo.findByTitleAndStatus(title, Status.active);
			if (product != null) {
				ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
				return productDTO;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return null;
	}

	public void updateProductInCategories(CategoryDTO categoryDTO) {
		try {
			LOGGER.info("Update Product");
			Optional<Category> productCategory = categoryRepo.findById(categoryDTO.getId());

			if (productCategory.isPresent()) {
				Category category=productCategory.get();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	            LocalDateTime now = LocalDateTime.now();
				Set<Product> productSet=category.getProducts();
				List<Product> productList =  modelMapper.map(categoryDTO.getProducts(),new TypeToken<List<Product>>()  {}.getType());
				
				
				categoryDTO.getProducts().forEach(productDTO->{
					
					Optional<Product> product=category.getProducts().stream().filter(prd-> prd.getId().equals(productDTO.getId())).findAny();
					
					product.get().setDescription(productDTO.getDescription());
					product.get().setTitle(productDTO.getTitle());
					product.get().setUpdated_at(dtf.format(now));
				});
				
				categoryRepo.save(category);
				
//				productList.forEach(productSets -> {
//					productSet.stream()
//		                        .filter(productLists -> productSets.getId().equals(productLists.getId()))
//		                        .findFirst()
//		                        .ifPresent(updatedProduct -> {
//		                        	productSets.setUpdated_at(dtf.format(now));
//		                        	productSets.setCreated_at(dtf.format(now));
//		                        });
//		            });categoryRepo.save(category);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		
	}

	public String inactiveProductById(Long id) {
		try {
			LOGGER.info("Get Inactive Products");
			Optional<Product> productOptional = productRepo.findById(id);
			if (productOptional.isPresent()) {
				Product product = productOptional.get();

				product.setStatus(Status.inactive);
				productRepo.save(product);
				return "Product Succesfully marked as inactive";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return "Product not found";
	}

}
