package com.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="prod_tab2")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	private Integer prodId;
	private String prodCode;
	private Double prodCost;
	
}
