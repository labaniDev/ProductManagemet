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

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Brand;
import com.example.demo.entity.Item;
import com.example.demo.entity.Product;
import com.example.demo.repository.BrandRepo;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.ProductRepo;
import java.util.Set;

@Service
public class ItemService {
	@Autowired
	ItemRepo itemRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	BrandRepo brandRepo;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);
	
	
	public void addItem(CategoryDTO categoryDTO) {
		try {
			LOGGER.info("Add Item");
			
			categoryDTO.getProducts().stream().forEach(prd->{
				Optional<Product> product=productRepo.findById(prd.getId());
				
				prd.getItems().stream().forEach(item->{
					
					Optional<Brand> brand=brandRepo.findById(item.getBrandid());
					if(product.isPresent()&&brand.isPresent()) {
						Product products=product.get();
						Brand brands=brand.get();
						Item itm=new Item();
						itm.setMrp(item.getMrp());
						itm.setDiscount(item.getDiscount());
						itm.setPrice(item.getPrice());
						 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
						  LocalDateTime now = LocalDateTime.now(); 
						  itm.setCreated_at(dtf.format(now));
						  itm.setUpdated_at(dtf.format(now));
						  itm.setProduct(products);
						  itm.setBrand(brands);
						
						itemRepo.save(itm);
					}
				});
			});
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
			
		}
	}

	public List<ItemDTO> getAllItems(Long id){
		try {
			LOGGER.info("Get All Items");
			Optional<Product> productOptional=productRepo.findById(id);
			if(productOptional.isPresent()) {
				Product product=productOptional.get();
				Set<Item> itemList=product.getItems();
				List<ItemDTO> itemdtoList=modelMapper.map(itemList,new TypeToken<List<ItemDTO>>() {}.getType() );
				return itemdtoList;
				}}catch(Exception ex) {
					ex.printStackTrace();
					LOGGER.error(ex.getMessage());
				}
				return null;
			}
			
			
		
	
	public ItemDTO getItemByItemId(Long itemid) {
		try {
			LOGGER.info("Update Item");
		Optional<Item> item=itemRepo.findById(itemid);
		if(item.isPresent()) {
			Item itemdto=item.get();
			ItemDTO productItem= modelMapper.map(itemdto,ItemDTO.class);
			return productItem;
			
		}}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return null;
	}
	
	public ItemDTO  getItemByProductId(Long productid) {
		try {
			LOGGER.info("Get item by productid");
		 Optional<Item> item=itemRepo.findById(productid);
		 if(item.isPresent()) {
			 Item  itemdto=item.get();
			 ItemDTO itemdtoList= modelMapper.map(itemdto,ItemDTO.class);
			return itemdtoList;
		 }
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return null;
	  }
	
	public Item updateItem(ItemDTO itemDTO) {
		try {
		LOGGER.info("Update Item");
			Optional<Item> itemlIST=itemRepo.findById(itemDTO.getItemid());
		
		if(itemlIST.isPresent()) {
		  Item itemList=itemlIST.get();
		  itemList.setMrp(itemDTO.getMrp());
		  itemList.setDiscount(itemDTO.getDiscount());
		  itemList.setPrice(itemDTO.getPrice());
		    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			  LocalDateTime now = LocalDateTime.now(); 
			  itemList.setCreated_at(dtf.format(now));
			  itemList.setUpdated_at(dtf.format(now));
			  return itemRepo.save(itemList);
		}
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return null;
	}
	
	public int deleteItemById( Long itemid) {
		try {
		   LOGGER.info("Delete Item By id");
		Optional<Item> item=itemRepo.findById(itemid);
		if(item.isPresent()) {
		itemRepo.deleteById(itemid);
		  return 0;
		}else
		  return 1;
		  }catch(Exception ex) {
			  ex.printStackTrace();
				LOGGER.error(ex.getMessage());  
		  }
		return 2;
	
	

}
}
