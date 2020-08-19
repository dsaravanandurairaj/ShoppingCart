package com.shopping.cart.Utils;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shopping.cart.DaoImpl.ShoppingDaoImpl;
import com.shopping.cart.ServiceImpl.ShoppingServiceImpl;


public class FacesUtils {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub		
		FacesUtils fb=new FacesUtils();
		fb.getSpringFactoryBean("shoppingService");
	}

	@SuppressWarnings("resource")
	public static Object getSpringFactoryBean(String Objname) {
		// TODO Auto-generated method stub
		
		System.out.println("method comming");
		ApplicationContext context=new ClassPathXmlApplicationContext("file:\\C:\\WorkspaceJSF\\ShoppingMart\\WebContent\\WEB-INF\\applicationContext.xml");
		ShoppingServiceImpl shoppingService=(ShoppingServiceImpl)context.getBean("shoppingService",ShoppingServiceImpl.class);
		ShoppingDaoImpl shoppingDaoImpl=(ShoppingDaoImpl)context.getBean("shoppingDao",ShoppingDaoImpl.class);
			
		Object obj=context.getBean(Objname);
		System.out.println("hi hihih "+obj);
		return obj;
		
	}

}
