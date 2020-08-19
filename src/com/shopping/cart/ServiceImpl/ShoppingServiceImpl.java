package com.shopping.cart.ServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.shopping.cart.Dao.ShoppingDao;
import com.shopping.cart.Service.ShoppingService;
@Service
@Component("shoppingService")
/*@Resource(name="shoppingService")*/
public class ShoppingServiceImpl implements ShoppingService{
	@Autowired
	private ShoppingDao shoppingDao;
	public ShoppingDao getShoppingDao() {
		return shoppingDao;
	}

	public void setShoppingDao(ShoppingDao shoppingDao) {
		this.shoppingDao = shoppingDao;
	}
	
	@Override
	public Object UserReginsert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("Service Imple Comming" +map);
		return shoppingDao.UserReginsert(map);
		
	}

	@Override
	public Object userLoginValidation(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("Service login data Comming" +map);
		return shoppingDao.userLoginValidation(map);
	}
	@Override
	public Object adminLoginValidation(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("Service login data Comming" +map);
		return shoppingDao.adminLoginValidation(map);
	}
	@Override
	public Object fileUploadInsert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("Service login data Comming" +map);
		return shoppingDao.fileUploadInsert(map);
	}
}
