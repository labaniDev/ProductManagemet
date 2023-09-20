package com.example.demo.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
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

public void createitem (ItemDTO itemdto) {
	Item item = modelMapper.map(itemdto, Item.class);
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  LocalDateTime now = LocalDateTime.now(); 
	  item.setCreated_at(dtf.format(now));
	  item.setUpdate_at(dtf.format(now));
	  itemRepo.save(item);
    }
}
