package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="category")
public class Category implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -2765525025891589913L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id;
//private Long parentid;
private String title;
private String created_at ;
private String updated_at ;




@ManyToMany(fetch = FetchType.LAZY,cascade = {
        CascadeType.ALL
    })
@JoinTable(name="category_product", joinColumns = { @JoinColumn(name = "categoryid")}, inverseJoinColumns = { @JoinColumn(name = "productid")})
private Set<Product> products = new HashSet<Product>();

}
