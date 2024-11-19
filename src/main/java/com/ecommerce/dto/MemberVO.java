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
public class MemberVO {
	String id;
	String password;
	String name;
	String email;
	String phone;
	boolean isMerchant;
}
