package com.shopping.cart.Bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.shopping.cart.Service.ShoppingService;
import com.shopping.cart.Utils.FacesUtils;

@ManagedBean(name = "fileUploadBean")
@SessionScoped
public class FileUploadBean {
	private Part file;
	private UploadedFile ufile;
	private String filename;
	private String mcategory;
	private String subcategory;
	private String proname;
	private int proprice;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getMcategory() {
		return mcategory;
	}
	public void setMcategory(String mcategory) {
		this.mcategory = mcategory;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	
	
	
	
	public int getProprice() {
		return proprice;
	}
	public void setProprice(int proprice) {
		this.proprice = proprice;
	}
	public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	
	public UploadedFile getUfile() {
		return ufile;
	}
	public void setUfile(UploadedFile ufile) {
		this.ufile = ufile;
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
		
		try {
		
		String filename="";
		filename=getImageFileName(file);
		
		System.out.println("filename comming== " +filename);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("filename",filename);
		map.put("mcategory", getMcategory());
		map.put("subcategory",getSubcategory() );
		map.put("proname", getProname());
		map.put("proprice", getProprice());
		System.out.println("Controller=== "+map);
		ShoppingService shoppingService = (ShoppingService)FacesUtils.getSpringFactoryBean("shoppingService");
		shoppingService.fileUploadInsert(map);
		
		}catch (Exception e) {
			// TODO: handle exception
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
