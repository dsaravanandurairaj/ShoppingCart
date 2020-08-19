package com.shopping.cart.Controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.shopping.cart.Bean.FileUploadBean;
import com.shopping.cart.Service.ShoppingService;
import com.shopping.cart.Utils.FacesUtils;
@ManagedBean(name = "fileUploadeController")
@SessionScoped

public class FileUploadeController implements Serializable{
	private static final long serialVersionUID = -5252698674910491604L;

	public FileUploadeController() {
		System.out.println("FileUploadeController()");
	}
	@ManagedProperty(value="#{fileUploadBean}")
	private FileUploadBean fileUploadBean;

	public FileUploadBean getFileUploadBean() {
		return fileUploadBean;
	}
	public void setFileUploadBean(FileUploadBean fileUploadBean) {
		this.fileUploadBean = fileUploadBean;
	}
	@Autowired
	@Qualifier("shoppingService")
	private ShoppingService shoppingService;
	public ShoppingService getShoppingService() {
		return shoppingService;
	}
	public void setShoppingService(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}
	public String fileUploadProduct() {
		System.out.println("fileupload Method comming====");
		try {
			String filename="";
			filename=getFileUploadBean().getFilename();
System.out.println(getFileUploadBean().getFile().getName());
			/*Map<String, Object> map=new HashMap<String, Object>();
			map.put("filename",getFileUploadBean().getFilename());
			map.put("mcategory", getFileUploadBean().getMcategory());
			map.put("subcategory",getFileUploadBean().getSubcategory() );
			map.put("proname", getFileUploadBean().getProname());
			map.put("proprice", getFileUploadBean().getProprice());
			System.out.println("Controller=== "+map);*/
			

		/*	if(shoppingService instanceof ShoppingService) {
				System.out.println("Instance Created");
			}else {System.out.println("null");}
			
			
		ShoppingService shoppingService = (ShoppingService)FacesUtils.getSpringFactoryBean("shoppingService");
			shoppingService.UserReginsert(map);*/
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
				
		return null;
		
	}
	
	private static String getImageFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

}
