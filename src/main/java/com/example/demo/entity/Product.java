package com.example.demo.entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name="product")
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6931746857783114222L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	//private Integer type;
	private String created_at;
	private String updated_at;
	@Enumerated(EnumType.STRING)
	private Status status;
	
//	@OneToOne(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
//	private Productdetails productdetails;
	
	@OneToMany(mappedBy="product")
	@JsonIgnore
    private Set<Item> items;
	
	@ManyToMany(mappedBy = "products",cascade = {
	        CascadeType.ALL
	    },fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Category> categories = new HashSet<>();


}
