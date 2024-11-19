/**
 * 
 */
package com.ecommerce.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ecommerce.dto.MemberVO;

/**
 * @author 04-14
 *
 */
public class MemberDAO {
	static final String salt = "Night Owl by Galimatias";
	DataSource dataSource;
	{
		dataSource = ConnectionPoolProvider.getDataSource();		
	}
	
	public boolean signup(MemberVO member) {
		String sql = "INSERT INTO member "
				+ "(id, password, name, email, phone)"
				+ "values"
				+ "(?, ?, ?, ?, ?)";
		try(Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			String id = member.getId();
			String password = sha256(member.getPassword());
			String name = member.getName();
			String email = member.getEmail();
			String phone = member.getPhone();
			
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			if(email == null) {
				preparedStatement.setNull(4, java.sql.Types.VARCHAR);
			} else {
				preparedStatement.setString(4, email);
			}
			if(phone == null) {
				preparedStatement.setNull(5, java.sql.Types.CHAR);
			} else {
				preparedStatement.setString(5, phone);
			}
			
			if(preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.err.println(sql);
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updatePermission(String id, boolean isMerchant) {
		String sql = "UPDATE member SET "
				+ "is_merchant = ? "
				+ "where"
				+ "id = ?";
		try(Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setBoolean(1, isMerchant);
			preparedStatement.setString(2, id);
			if(preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.err.println(sql);
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public List<MemberVO> readAllUser(){
		String sql = "SELECT id, name, email, phone, is_merchant FROM member";
		try(Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery())
		{
			List<MemberVO> members = new ArrayList<>();
			while(resultSet.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setId(resultSet.getString("id"));
				memberVO.setName(resultSet.getString("name"));
				memberVO.setEmail(resultSet.getString("email"));
				memberVO.setPhone(resultSet.getString("phone"));
				memberVO.setMerchant(resultSet.getBoolean("is_merchant"));
				members.add(memberVO);
			}
			return members;
			
		} catch (SQLException e) {
			System.err.println(sql);
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public MemberVO login(String id, String password) {
		String sql = "SELECT name FROM member WHERE id = ? and password = ?";
		password = sha256(password);
		try(Connection connection = dataSource.getConnection();				
			PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setString(1,id);
			preparedStatement.setString(2, password);
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.next()) {
					MemberVO member = new MemberVO();
					member.setName(resultSet.getString("name"));
					member.setMerchant(resultSet.getBoolean("is_merchant"));
					return member;
				}
			}
		} catch (SQLException e) {
			System.err.println(sql);
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public static String sha256(String source) {
		//for password hashing
		try {
			byte[] bytes = (source + salt).getBytes();
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(bytes);
			byte[] hashed = digest.digest();
			
			StringBuilder hexString = new StringBuilder();
	        for (byte b : hashed) {
	            String hex = Integer.toHexString(0xff & b);
	            if (hex.length() == 1) {
	                hexString.append('0');
	            }
	            hexString.append(hex);
	        }

	        return hexString.toString();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Algorithm typo", e);
		}
		
		
	}
}
