/**
 * 
 */
package com.ecommerce.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 04-14
 *
 */
@Getter @Setter
public class ReviewVO {
	String writer;
	String content;
	Timestamp writeTime;
}
