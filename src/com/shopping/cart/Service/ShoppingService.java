package com.shopping.cart.Service;

import java.util.Map;

public interface ShoppingService {
	public Object UserReginsert(Map<String, Object> map);
	public Object userLoginValidation(Map<String, Object> map);
	public Object adminLoginValidation(Map<String, Object> map);
	public Object fileUploadInsert(Map<String, Object> map);
}
