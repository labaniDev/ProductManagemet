package com.example.demo.service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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
import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Brand;
import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.entity.Product;
import com.example.demo.entity.Status;
import com.example.demo.repository.BrandRepo;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.ProductRepo;
import java.util.Set;
import java.util.stream.Collectors;

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
	@Autowired
	CategoryRepo categoryRepo;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void addItem(CategoryDTO categoryDTO) {
		try {
			LOGGER.debug("Inside addItem::"+categoryDTO.toString());         // To add item by categoryId,productId,BrandId
			Optional<Category> categoryOptional=categoryRepo.findById(categoryDTO.getId());
			LOGGER.error("Give Correct CategoryId"+categoryDTO.getId());
			if(categoryOptional.isPresent()) {
				Category category=categoryOptional.get();
			}
			categoryDTO.getProducts().stream().forEach(prd->{
				Optional<Product> product=productRepo.findById(prd.getId());
				
				prd.getItems().stream().forEach(item->{
					Optional<Brand> brand=brandRepo.findById(item.getBrand().getId());
					LOGGER.info("brandid:"+item.getBrand().getId());
					if(product.isPresent()&& brand.isPresent()) {
						Product products=product.get();
						Brand brands=brand.get();
						Item itm=new Item();
						itm.setTitle(item.getTitle());
						itm.setMrp(item.getMrp());
						itm.setDiscount(item.getDiscount());
						itm.setPrice(item.getPrice());
						 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
						  LocalDateTime now = LocalDateTime.now(); 
						  itm.setCreated_at(dtf.format(now));
						  itm.setUpdated_at(dtf.format(now));
						  itm.setStatus(Status.active);
						  itm.setProduct(products);
						  itm.setBrand(brands);
						itemRepo.save(itm);
						LOGGER.debug("Item added Successfully");
					}
				});
			});
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception in addItem::"+ex.getMessage());
			
		}
	}

	public List<ItemDTO> getAllItems(Long id){
		try {
			LOGGER.info("Get All Items By ProductId");     //To get Item by ProductId
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
			
	public List<ItemDTO> getAllItems()	{
		try {
			LOGGER.info("Get All Items");                 //To get All Active Items
			List<Item> itemList=itemRepo.findAll();
			List<ItemDTO> ItemDTOlist = itemList.stream()
	                .filter(item -> item.getStatus() == Status.active)
	                .map(item -> modelMapper.map(item, ItemDTO.class))
	                .collect(Collectors.toList());

	        return ItemDTOlist;
		}catch (Exception ex) {
	        ex.printStackTrace();
	        LOGGER.error(ex.getMessage());
	        return Collections.emptyList();}
	}
		
	
//	public ItemDTO getItemByItemId(Long itemid) {
//		try {
//			LOGGER.info("Update Item");
//		Optional<Item> item=itemRepo.findById(itemid);
//		if(item.isPresent()) {
//			Item itemdto=item.get();
//			ItemDTO productItem= modelMapper.map(itemdto,ItemDTO.class);
//			return productItem;
//			
//		}}catch(Exception ex) {
//			ex.printStackTrace();
//			LOGGER.error(ex.getMessage());
//		}
//		return null;
//	}
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void updateItem(ItemDTO itemDTO) {
		try {
		LOGGER.debug("Inside UpdateItem::"+itemDTO.toString());
		
				Optional<Item> itemOptional=itemRepo.findById(itemDTO.getId());
				Optional<Brand> brandOptional=brandRepo.findById(itemDTO.getBrand().getId());
	
				
				if(itemOptional.isPresent()&& brandOptional.isPresent()) {
					Item item=itemOptional.get();
					Brand newBrand=brandOptional.get();
					
					LOGGER.info("Item id: " +item.getId());
					LOGGER.info("Before Update - MRP: " + item.getMrp() + ", Price: " + item.getPrice() + ", Discount: " + item.getDiscount()+item.getTitle());
	                LOGGER.info("After Update - title: "+itemDTO.getTitle());
						item.setMrp(itemDTO.getMrp());
						item.setPrice(itemDTO.getPrice());
						item.setDiscount(itemDTO.getDiscount());
						item.setTitle(itemDTO.getTitle());
						LOGGER.info(itemDTO.getTitle());
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			            LocalDateTime now = LocalDateTime.now();
			            item.setUpdated_at(dtf.format(now));
			            item.setBrand(newBrand);
			            itemRepo.save(item); 
			            LOGGER.debug("Item Updated Successfully");
        
    }}catch(Exception ex) {
    	
    	
			ex.printStackTrace();
			LOGGER.error("Exception in Update Item::"+ex.getMessage());}
  		}
	
	
	public String inActiveItemById( Long id) {
		try {
		   LOGGER.info("Inactive Item By id");
		Optional<Item> itemOptional=itemRepo.findById(id);
		if(itemOptional.isPresent()) {
		Item item=itemOptional.get();
		item.setStatus(Status.inactive);
		itemRepo.save(item);
		  return "Item successfully marked as inactive";
		}
		  }catch(Exception ex) {
			  ex.printStackTrace();
				LOGGER.error(ex.getMessage());  
		  }
		return "Item already iactivated";
		
	
	

}
	public void updateItemPrice(ItemDTO itemDTO) {
		Optional<Item> itemOptional=itemRepo.findById(itemDTO.getId());
		if(itemOptional.isPresent()) {
			Item item=itemOptional.get();
			item.setPrice(itemDTO.getPrice());
			itemRepo.save(item);
		}
	}
}
