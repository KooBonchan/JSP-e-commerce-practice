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
	
	private DataSource dataSource;
	{
		dataSource = ConnectionPoolProvider.getDataSource();
	}
	
	public boolean createProduct(ProductVO productVO, String merchantId) {
		String sql = "INSERT INTO product "
				+ "(merchant_id, name, price, img_path, description, inventory) "
				+ "values"
				+ "(?, ?, ?, ?, ?, ?)";
		try(Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, merchantId);
			preparedStatement.setString(2, productVO.getName());
			preparedStatement.setInt(3, productVO.getPrice());
			preparedStatement.setString(4, productVO.getImagePath());
			preparedStatement.setString(5, productVO.getDescription());
			preparedStatement.setInt(6, productVO.getInventory());
			int result = preparedStatement.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			System.err.println(sql);
			System.err.println(e.getMessage());
		}
		return false;
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
					productVO.setProviderName(resultSet.getString("provider"));
					productVO.setId(resultSet.getInt("id"));
					productVO.setName(resultSet.getString("name"));
					productVO.setPrice(resultSet.getInt("price"));
					productVO.setDescription(resultSet.getString("description"));
					productVO.setImageThumbnailPath(resultSet.getString("img_path"));
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
	
	public List<ProductVO> readMyProducts(String provider){
		String sql = "select "
				+ "prod_id, name, price, description, img_path, inventory "
				+ "from product "
				+ "where merchant_id = ?";
		List<ProductVO> products = new ArrayList<>(PAGE_SIZE);
		try(Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, provider);
			try(ResultSet resultSet = preparedStatement.executeQuery())
			{
				
				while(resultSet.next()) {
					ProductVO productVO = new ProductVO();
					productVO.setProviderName(provider);
					productVO.setId(resultSet.getInt("prod_id"));
					productVO.setName(resultSet.getString("name"));
					productVO.setPrice(resultSet.getInt("price"));
					productVO.setDescription(resultSet.getString("description"));
					productVO.setImageThumbnailPath(resultSet.getString("img_path"));
					productVO.setInventory(resultSet.getInt("inventory"));
					products.add(productVO);
				}
				
			}
		} catch (SQLException e) {
			System.err.println(sql);
			System.err.println(e.getMessage());
		}
		return products;
	}
	
	public ProductVO readProduct(int id){
		String sql = "select "
				+ "provider, name, price, description, img_path, inventory "
				+ "from product_view "
				+ "where id = ?";
		try(Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1,id);
			try(ResultSet resultSet = preparedStatement.executeQuery())
			{
				if(resultSet.next()) {
					ProductVO productVO = new ProductVO();
					productVO.setProviderName(resultSet.getString("provider"));
					productVO.setName(resultSet.getString("name"));
					productVO.setPrice(resultSet.getInt("price")); 
					productVO.setDescription(resultSet.getString("description"));
					productVO.setImageFullPath(resultSet.getString("img_path"));
					productVO.setInventory(resultSet.getInt("inventory"));
					return productVO;
				}
			}
		} catch (SQLException e) {
			System.err.println(sql);
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public boolean updateProduct(ProductVO productVO, String merchantId) {
		String sql = "UPDATE product SET "
				+ "name = ?, "
				+ "price = ?, "
				+ "description = ?, "
				+ "inventory = ?"
				+ (productVO.getImagePath() == null ? "" : ", img_path = ?")
				+ " WHERE merchant_id = ? and prod_id = ? ";
		
		try(Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			int index = 1;
			preparedStatement.setString(index++, productVO.getName());
			preparedStatement.setInt(index++, productVO.getPrice());
			preparedStatement.setString(index++, productVO.getDescription());
			preparedStatement.setInt(index++, productVO.getInventory());
			
			if(productVO.getImagePath() != null) {
				preparedStatement.setString(index++, productVO.getImagePath());
			}
			preparedStatement.setString(index++, merchantId);
			preparedStatement.setInt(index++, productVO.getId());
			
			int result = preparedStatement.executeUpdate();
			
			return result > 0;
		} catch (SQLException e) {
			System.err.println(sql);
			System.err.println(e.getMessage());
		}
		return false;
	}
}
