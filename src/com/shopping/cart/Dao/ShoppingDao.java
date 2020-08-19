package com.shopping.cart.Dao;

import java.util.Map;

public interface ShoppingDao {
	public Object UserReginsert(Map<String, Object> map);
	public Object userLoginValidation(Map<String, Object> map);
	public Object adminLoginValidation(Map<String, Object> map);
	public Object fileUploadInsert(Map<String, Object> map);
	
}
