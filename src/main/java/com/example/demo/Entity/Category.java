package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="category")
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Integer categoryid;
private String title;
private String created_at ;
private String update_at ;

}
