/**
 * 
 */
package com.ecommerce.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

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
	
	public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("writer", this.writer);
        jsonObject.put("content", this.content);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(this.writeTime);
        jsonObject.put("writeTime", formattedDate);
        return jsonObject;
    }
}