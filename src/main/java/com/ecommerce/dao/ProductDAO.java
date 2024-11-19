/**
 * 
 */
package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ecommerce.dto.ProductVO;

import lombok.Setter;

/**
 * @author 04-14
 *
 */
public class ProductDAO {
	@Setter
	static int PAGE_SIZE = 18;
	
	DataSource dataSource;
	{
		dataSource = ConnectionPoolProvider.getDataSource();
	}
	
	public List<ProductVO> readProductPage(int page){
		String sql = "select "
				+ "provider, id, name, price, description, img_path, inventory "
				+ "from product_view "
				+ "limit ? offset ?";
		try(Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			int offset = PAGE_SIZE * (page - 1);
			preparedStatement.setInt(1, PAGE_SIZE);
			preparedStatement.setInt(2, offset);
			try(ResultSet resultSet = preparedStatement.executeQuery())
			{
				List<ProductVO> products = new ArrayList<>(PAGE_SIZE);
				while(resultSet.next()) {
					ProductVO productVO = new ProductVO();
					productVO.setProvider(resultSet.getString("provider"));
					productVO.setId(resultSet.getInt("id"));
					productVO.setName(resultSet.getString("name"));
					productVO.setPrice(resultSet.getInt("price"));
					productVO.setDescription(resultSet.getString("description"));
					productVO.setImagePath(resultSet.getString("img_path"));
					productVO.setInventory(resultSet.getInt("inventory"));
					products.add(productVO);
				}
				return products;
			}
		} catch (SQLException e) {
			System.err.println(sql);
			System.err.println(e.getMessage());
		}
		return null;
	}
}
