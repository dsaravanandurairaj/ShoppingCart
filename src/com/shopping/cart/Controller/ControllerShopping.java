package com.shopping.cart.Controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.shopping.cart.Bean.FileUploadBean;
import com.shopping.cart.Bean.RegisterBean;
import com.shopping.cart.Service.ShoppingService;
import com.shopping.cart.Utils.FacesUtils;

@ManagedBean(name = "controllerShopping")
@ViewScoped

public class ControllerShopping implements Serializable{

	private static final long serialVersionUID = -5252698674910491604L;

	public ControllerShopping() {
		System.out.println("ControllerShopping()");
	}
	
	@ManagedProperty("#{registerBean}")
	private RegisterBean registerBean;
	public RegisterBean getRegisterBean() {
		return registerBean;
	}
	public void setRegisterBean(RegisterBean registerBean) {
		this.registerBean = registerBean;
	}
	/*@ManagedProperty("#{fileUploadBean}")
	private FileUploadBean fileUploadBean;

	public FileUploadBean getFileUploadBean() {
		return fileUploadBean;
	}
	public void setFileUploadBean(FileUploadBean fileUploadBean) {
		this.fileUploadBean = fileUploadBean;
	}*/
	@Autowired
	@Qualifier("shoppingService")
	private ShoppingService shoppingService;
	public ShoppingService getShoppingService() {
		return shoppingService;
	}
	public void setShoppingService(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}
	public String regSubmit() {
		System.out.println("regSubmit Method comming====");
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("firstname", getRegisterBean().getFirstname());
			map.put("lastname", getRegisterBean().getLastname());
			map.put("username", getRegisterBean().getUsername());
			map.put("password", getRegisterBean().getPassword());
			map.put("email", getRegisterBean().getEmail());
			System.out.println("Controller=== "+map);
			

			if(shoppingService instanceof ShoppingService) {
				System.out.println("Instance Created");
			}else {System.out.println("null");}
			
			
		ShoppingService shoppingService = (ShoppingService)FacesUtils.getSpringFactoryBean("shoppingService");
			shoppingService.UserReginsert(map);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
				
		return null;
		
	}
	public String userLogin() {
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			
			map.put("username", getRegisterBean().getUsername());
			map.put("password", getRegisterBean().getPassword());		
			System.out.println("Controller=== "+map);
			
			
		ShoppingService shoppingService = (ShoppingService)FacesUtils.getSpringFactoryBean("shoppingService");
		Object obj=	shoppingService.userLoginValidation(map);
		System.out.println("object result" +obj);
		boolean status=(boolean) obj;
		System.out.println("ststus controller" +status);
			if(status) {
				return "/pages/loginHome";
			}else {
				System.out.println("iNvalid");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	public String adminLogin() {
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			
			map.put("username", getRegisterBean().getUsername());
			map.put("password", getRegisterBean().getPassword());		
			System.out.println("Controller=== "+map);
			
			
		ShoppingService shoppingService = (ShoppingService)FacesUtils.getSpringFactoryBean("shoppingService");
		Object obj=	shoppingService.adminLoginValidation(map);
		System.out.println("object result" +obj);
		boolean status=(boolean) obj;
		System.out.println("ststus controller" +status);
			if(status) {
				return "/pages/adminHome";
			}else {
				System.out.println("invalid");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	/*public String fileUploadProduct() {
		System.out.println("regSubmit Method comming====");
		try {
			
		
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("filename",getFileUploadBean().getFilename());
			map.put("mcategory", getFileUploadBean().getMcategory());
			map.put("subcategory",getFileUploadBean().getSubcategory() );
			map.put("proname", getFileUploadBean().getProname());
			map.put("proprice", getFileUploadBean().getProprice());
			System.out.println("Controller=== "+map);
			

			if(shoppingService instanceof ShoppingService) {
				System.out.println("Instance Created");
			}else {System.out.println("null");}
			
			
		ShoppingService shoppingService = (ShoppingService)FacesUtils.getSpringFactoryBean("shoppingService");
			shoppingService.UserReginsert(map);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
				
		return null;
		
	}*/
	
}
