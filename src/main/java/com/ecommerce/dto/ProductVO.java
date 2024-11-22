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
	static final String PATH = "uploads/";
	int id;
	private String providerName;
	private String name;
	private int price;
	private String description;
	private int inventory;
	private String imagePath;
	
	public void setImageFullPath(String filename) {
		imagePath = PATH + filename;
	}
	
}
