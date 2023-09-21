package com.example.demo.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.Item;
import com.example.demo.Model.ItemDTO;
import com.example.demo.Repository.ItemRepo;

@Service
public class ItemService {

@Autowired
ItemRepo itemRepo;
@Autowired
ModelMapper modelMapper;

public void createitem(ItemDTO itemdto){
	Item item = modelMapper.map(itemdto,Item.class);
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  LocalDateTime now = LocalDateTime.now();  
	  item.setCreated_at(dtf.format(now));
	  item.setUpdate_at(dtf.format(now));
	 itemRepo.save(item);
     }
public int deleteItemById( long itemid) {
	  Optional<Item> itemoptional = itemRepo.findById(itemid);
if(itemoptional.isPresent()) {
	itemRepo.deleteById(itemid);
	  return 0;
}else
	  return 1;
  }
public  List<ItemDTO> getAllitems(){
	List<Item> itemList=itemRepo.findAll();	
	List<ItemDTO> itemdtoList= modelMapper.map(itemList,new TypeToken<List<ItemDTO>>() {}.getType() );
     return itemdtoList;
     } 
public ItemDTO getItemsById(Long itemid) {
	 Optional<Item> item = itemRepo.findById(itemid);
	     if(item.isPresent()) {
	    	 ItemDTO itemdto= modelMapper.map(item,ItemDTO.class);	 
		  return itemdto;
	     }
	 return null;
    }
}
