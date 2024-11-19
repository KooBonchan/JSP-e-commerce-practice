/**
 * 
 */
package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ecommerce.dto.ReviewVO;


/**
 * @author 04-14
 *
 */
public class ReviewDAO {
	static int BLOCK_SIZE = 10;
	
	DataSource dataSource;
	{
		dataSource = ConnectionPoolProvider.getDataSource();
	}
	
	public List<ReviewVO> readReviewBlock(int prod_id, int block){
		String sql = "select "
				+ "mem_id, content, write_time "
				+ "from product_view "
				+ "where prod_id = ? "
				+ "limit ? offset ? ";
		try(Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			int offset = BLOCK_SIZE * (block - 1);
			preparedStatement.setInt(1, prod_id);
			preparedStatement.setInt(2, BLOCK_SIZE);
			preparedStatement.setInt(3, offset);
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				List<ReviewVO> reviews = new ArrayList<>(BLOCK_SIZE);
				while(resultSet.next()) {
					String writer = resultSet.getString("writer");
					String content = resultSet.getString("content");
					Timestamp writeTime = resultSet.getTimestamp("write_time");
					ReviewVO vo = new ReviewVO();
					vo.setWriter(writer);
					vo.setContent(content);
					vo.setWriteTime(writeTime);
					
					reviews.add(vo);
				}
				if(reviews.size() > 0) { return reviews; }
			}
		} catch (SQLException e) {
			System.err.println(sql);
			System.err.println(e.getMessage());
		}
		return null;
	}
}
