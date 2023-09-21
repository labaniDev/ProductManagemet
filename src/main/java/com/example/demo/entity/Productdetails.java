package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;



import lombok.Data;

@Entity
@Data
public class Productdetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productdetailsid;
	private String created_at;
	private String updated_at;

	
	@OneToOne
    @JoinColumn(name="productid")
    private Product product;

	
}
