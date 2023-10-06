package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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

	PropertyMap<ProductDTO, Product> skipModifiedFieldsMap = new PropertyMap<ProductDTO, Product>() {
		protected void configure() {
			skip().setCategories(null);
		}
	};

	public static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	public void addProduct(CategoryDTO categoryDTO) {
		try {
			LOGGER.info("Adding product" + categoryDTO);

			modelMapper.addMappings(skipModifiedFieldsMap);
			//Product product = modelMapper.map(productDTO, Product.class);
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//			LocalDateTime now = LocalDateTime.now();

//			product.setCreated_at(dtf.format(now));
//			product.setUpdated_at(dtf.format(now));

//		  Set<Category> categories=new HashSet<>();
			//if (productDTO.getCategories() != null) {

				Set<Category> categorySet = new HashSet<Category>();

				categoryDTO.getProducts().stream().forEach(cate -> {
					Optional<Category> productCategory = categoryRepo.findById(cate.getId());
					if (productCategory.isPresent()) {
						Product prod=new Product();
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						prod.setCreated_at(dtf.format(now));
						prod.setUpdated_at(dtf.format(now));
						prod.setStatus(Status.active);
						
						productCategory.get().getProducts().add(prod);
					}
					
					categoryRepo.save(productCategory.get());
				});
			//}

		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());

		}

	}

	public List<Product> getAllProduct() {
		//try {
			LOGGER.info("Get All Product");
			List<Product> products = productRepo.findAll();
			//List<ProductDTO> productDTOList = modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
			//}.getType());
			//for (ProductDTO dto : productDTOList) {
			//dto.setCategories(modelMapper.map(products.stream().filter(prd -> prd.getId().equals(dto.getId()))
				//	.findAny().get().getCategories(), new TypeToken<List<CategoryDTO>>() {
					//	}.getType()));
			//}
			return products;
		}// catch (Exception ex) {
			//ex.printStackTrace();
			//LOGGER.error(ex.getMessage());
		//}
		//return null;
	//}

	public ProductDTO getProductByCategoryId(Long categoryid) {
		try {
			LOGGER.trace("Find Product By Categoryid");
			Optional<Product> product = productRepo.findById(categoryid);
			if (product.isPresent()) {
				Product productdto = product.get();
				ProductDTO productdtoList = modelMapper.map(productdto, ProductDTO.class);
				return productdtoList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return null;
	}
//  public ProductDTO getProductByProductId(Long Productid) {
//	  try {
//	  LOGGER.info("FIND Product By productid");
//	  Optional<Product> productOptional=productRepo.findById(Productid);
//	  if(productOptional.isPresent()) {
//		  Product productdto=productOptional.get();
//		  ProductDTO productDTOlist=modelMapper.map(productdto,ProductDTO.class);
//		  return productDTOlist;
//	  }}catch(Exception ex) {
//		  ex.printStackTrace();
//		  LOGGER.error(ex.getMessage());
//	  }
//	return null;
//  }

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

	public Product updateProduct(ProductDTO productDTO) {
		try {
			LOGGER.info("Update Product");
			Optional<Product> productOptional = productRepo.findById(productDTO.getId());

			if (productOptional.isPresent()) {
				Product newProduct = productOptional.get();
				newProduct.setTitle(productDTO.getTitle());
				newProduct.setDescription(productDTO.getDescription());
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();

				newProduct.setCreated_at(dtf.format(now));
				newProduct.setUpdated_at(dtf.format(now));
				return productRepo.save(newProduct);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return null;
	}

	public String deleteProduct(Long productid) {
		try {
			LOGGER.info("Get Inactive Products");
			Optional<Product> productOptional = productRepo.findById(productid);
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
