package com.osf.test.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.osf.test.dao.FoodDAO;
import com.osf.test.db.DBCon;
import com.osf.test.vo.Food;

public class FoodDAOImpl implements FoodDAO {
	private static String SELECT_FOOD_LIST = "select * from food";
	private static String SELECT_FOOD_BY_FOOD_NUM ="select * from food where food_num=?";
	private static String INSERT_FOOD ="insert into food(food_num,food_name,food_price) "
			+ " values(seq_food_num.nextval,?,?)";
	private static String UPDATE_FOOD ="update food set food_name=?,food_price=? where food_num=?";
	private static String DELETE_FOOD ="delete from food where food_num=?";
	
	@Override
	public List<Food> selectFoodList() {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(SELECT_FOOD_LIST);			
			ResultSet rs = ps.executeQuery();
			List<Food> foodList = new ArrayList<>();
			while(rs.next()) {
				Food f = new Food();
				f.setFoodNum(rs.getInt("food_num"));
				f.setFoodName(rs.getString("food_name"));
				f.setFoodPrice(rs.getInt("food_price"));
				foodList.add(f);
			}
			return foodList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Food selectFood(Integer foodNum) {
		PreparedStatement ps;
		try {
			ps = DBCon.getCon().prepareStatement(SELECT_FOOD_BY_FOOD_NUM);
			ps.setInt(1, foodNum);
			ResultSet rs = ps.executeQuery();
			Food f = new Food();
			if(rs.next()) {
				
				f.setFoodNum(rs.getInt("food_num"));
				f.setFoodName(rs.getString("food_name"));
				f.setFoodPrice(rs.getInt("food_price"));					
			}
			return f;
		} catch (SQLException e) {			
			e.printStackTrace();
		}			
		
		return null;
	}

	@Override
	public int insertFood(Food food) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(INSERT_FOOD);
			ps.setString(1, food.getFoodName());
			ps.setInt(2, food.getFoodPrice());						
			return ps.executeUpdate();
	} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int updateFood(Food food) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(UPDATE_FOOD);
			ps.setString(1, food.getFoodName());
			ps.setInt(2, food.getFoodPrice());	
			ps.setInt(3,food.getFoodNum());
			return ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteFood(Integer foodNum) {
		PreparedStatement ps;
		try {
			ps = DBCon.getCon().prepareStatement(DELETE_FOOD);
			ps.setInt(1,foodNum);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return 0;
	}
	
	public static void main(String[] args) {
		FoodDAO fdao = new FoodDAOImpl();
		System.out.println(fdao.selectFoodList());
		
		Food f = new Food();
		f.setFoodName("삼겹살");
		f.setFoodPrice(10000);
		System.out.println(fdao.insertFood(f));
	
		
	}
}
