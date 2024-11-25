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
	static final String THUMBNAIL_PATH = "uploads/thumbnail/";
	int id;
	private String providerName;
	private String name;
	private int price;
	private String description;
	private int inventory;
	private String imagePath;
	
	public void setImageThumbnailPath(String filename) {
		imagePath = THUMBNAIL_PATH + filename;
	}
	public void setImageFullPath(String filename) {
		imagePath = PATH + filename;
	}
	
}
