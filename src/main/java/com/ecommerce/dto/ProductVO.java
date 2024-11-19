/**
 * 
 */
package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 04-14
 *
 */
@Getter @Setter
public class ProductVO {
	int id;
	String provider;
	String name;
	int price;
	String description;
	int inventory;
	
	String imagePath;
	
}
