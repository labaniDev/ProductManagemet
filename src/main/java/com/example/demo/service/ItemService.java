package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.Product;
import com.example.demo.repository.ItemRepo;
import com.example.demo.repository.ProductRepo;

@Service
public class ItemService {
	@Autowired
	ItemRepo itemRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ProductRepo productRepo;
	
	public void addItem(ItemDTO itemDTO) {
		Optional<Product> product=productRepo.findById(itemDTO.getProductid());
		if(product.isPresent()) {
			Product products=product.get();
			Item item=new Item();
			item.setMrp(itemDTO.getMrp());
			item.setDiscount(itemDTO.getDiscount());
			item.setPrice(itemDTO.getPrice());
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			  LocalDateTime now = LocalDateTime.now(); 
			  item.setCreated_at(dtf.format(now));
			  item.setUpdated_at(dtf.format(now));
			  item.setProduct(products);
			
			itemRepo.save(item);
		}
	}

	public List<ItemDTO> getAllItems(){
		List<Item> itemList=itemRepo.findAll();
		List<ItemDTO> itemdtoList=modelMapper.map(itemList,new TypeToken<List<ItemDTO>>() {}.getType() );
		return itemdtoList;
	}
	
	public Item updateItem(ItemDTO itemDTO) {
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
		return null;
	}
	
	public int deleteItemById( long itemid) {
		  Optional<Item> itemoptional = itemRepo.findById(itemid);
		if(itemoptional.isPresent()) {
		itemRepo.deleteById(itemid);
		  return 0;
		}else
		  return 1;
		  }
	
	

}
