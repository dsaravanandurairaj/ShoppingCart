package com.shopping.cart.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.shopping.cart.Bean.FileUploadBean;
import com.shopping.cart.Dao.ShoppingDao;
import com.shopping.cart.DaoImpl.ShoppingDaoImpl.FileStoreBean;
@Repository
@Component("shoppingDao")
@ManagedBean(name = "shoppingDaoImpl")
public class ShoppingDaoImpl implements ShoppingDao{

	 @Autowired
		private SessionFactory sessionFactory;
	     
	    public void setSessionFactory(SessionFactory sf){
	        this.sessionFactory = sf;
	    }
	 
	public SessionFactory getSessionFactory() {
			return sessionFactory;
		}
	@Autowired
	private DataSource ds;
	public DataSource getDs() {
		return ds;
	}
	public void setDs(DataSource ds) {
		this.ds = ds;
	}
	
	public Object UserReginsert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("Dao--- imple " +map);
		final String firstname=(String) map.get("firstname");
		final String lastname=(String) map.get("lastname");
		final String username=(String) map.get("username");
		final String password=(String) map.get("password");
		final String email=(String) map.get("email");
		String insertsql = "insert into USERREGISTRATION ( firstname, lastname,username,"

					+ "password,email) "

					+ "values (?,?,?,?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDs());

			jdbcTemplate.update(insertsql, new PreparedStatementSetter() {

				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, firstname);
					ps.setString(2, lastname);
					ps.setString(3, username);
					ps.setString(4, password);
					ps.setString(5, email);

				}

			});
		return null;
	}
	
	public Object userLoginValidation(Map<String, Object> map) {
		System.out.println("Dao--login- imple " +map);
		final String username=(String) map.get("username");
		final String password=(String) map.get("password");
		
		String query = "Select username,password from shoppingcart.USERREGISTRATION where username = ? and password = ?";
		PreparedStatement pstmt;
		ResultSet resultSet;
		boolean status=false;
		try {
			pstmt = ds.getConnection().prepareStatement(query);
			System.out.println("past =====" +pstmt);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			 resultSet = pstmt.executeQuery();
			 status = resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
		
	
		
		
	}
	
	public Object adminLoginValidation(Map<String, Object> map) {
		System.out.println("Dao--login- imple " +map);
		final String username=(String) map.get("username");
		final String password=(String) map.get("password");
		
		String query = "Select * from shoppingcart.adminregistration where username = ? and password = ?";
		PreparedStatement pstmt;
		ResultSet resultSet;
		boolean status=false;
		try {
			pstmt = ds.getConnection().prepareStatement(query);
			System.out.println("past =====" +pstmt);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			 resultSet = pstmt.executeQuery();
			 status = resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
		
	
		
		
	}

	public Object fileUploadInsert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("Dao--- imple " +map);
		final String filename=(String) map.get("filename");
		final String mcategory=(String) map.get("mcategory");
		final String subcategory=(String) map.get("subcategory");
		final String proname=(String) map.get("proname");
		final int proprice=(int) map.get("proprice");
		System.out.println("file name==" +filename);
		System.out.println(proprice);
		String insertsql = "insert into adminaddproducct( filename, mcategory,subcategory,"

					+ "proname,proprice) "

					+ "values (?,?,?,?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDs());

			jdbcTemplate.update(insertsql, new PreparedStatementSetter() {

				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, filename);
					ps.setString(2, mcategory);
					ps.setString(3, subcategory);
					ps.setString(4, proname);
					ps.setInt(5, proprice);

				}

			});
		return null;
	}
	

	public List getperInfoAll() throws Exception {
		
		int i = 0;
		List perInfoAll = new ArrayList(); 
		Connection con;
		PreparedStatement ps;
		Statement ps1;
		ResultSet rs;
		System.out.println("perInfoAll+++ ");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","root");
				ps1 = (Statement) con.createStatement();
				 rs = ps1.executeQuery("Select * from shoppingcart.adminaddproducct");
				boolean found=false;
				while(rs.next())
				  {
					FileStoreBean e = new FileStoreBean();
	                e.setFilename(rs.getString("filename"));
	                e.setMcategory(rs.getString("mcategory"));
	                e.setSubcategory(rs.getString("subcategory"));
	                e.setProname(rs.getString("proname"));
	                e.setProprice(rs.getInt("proprice"));
	                
	                perInfoAll.add(e);
	                found = true;
			 }
				rs.close();
				
				
				if (found) {
	                return perInfoAll;
	            } else {
	                return null; // no entires found
	            }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return perInfoAll;
		
		}
	
	

	/*public static ArrayList<FileStoreBean> getperInfoAll() throws Exception {
		
		int i = 0;
		Connection con;
		System.out.println("perInfoAll+++ ");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","root");
			
				ArrayList<FileStoreBean> al = new ArrayList<FileStoreBean>();
				String query = "select * from shoppingcart.adminregistration where username = ? and password = ?";
				PreparedStatement pstmt;
				ResultSet resultSet;
				boolean found=false;
				pstmt = ((Statement) con).getConnection().prepareStatement(query);
				
				while(resultSet.next())
				  {
					FileStoreBean e = new FileStoreBean();
	                e.setFilename(resultSet.getString("filename"));
	                e.setProname(resultSet.getString("proname"));
	                e.setProprice(resultSet.getInt("proprice"));
	                
	                al.add(e);
	                found = true;
			 }
				resultSet.close();
				if (found) {
	                return al;
	            } else {
	                return null; // no entires found
	            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	*/
	
	@ManagedBean(name = "fileStoreBean")
	@SessionScoped
	public class FileStoreBean{
		private String filename;
		private String mcategory;
		private String subcategory;
		private String proname;
		private int proprice;
		
		
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
		public FileStoreBean(String filename, String proname, int proprice) {
			// TODO Auto-generated constructor stub
			this.filename=filename;
			
			this.proname=proname;
			this.proprice=proprice;
			
			System.out.println("filename " +filename);
			System.out.println("filename " +proname);
			System.out.println("filename " +proprice);
		}
		public FileStoreBean() {
			// TODO Auto-generated constructor stub
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
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
		
	}
	public void onEdit(RowEditEvent event) throws Exception {  
		FileStoreBean file=new FileStoreBean();
		final String filename=((FileStoreBean) event.getObject()).getFilename();
		final String mcategory= ((FileStoreBean) event.getObject()).getMcategory();
		final String subcategory= ((FileStoreBean) event.getObject()).getSubcategory();
		final String proname= ((FileStoreBean) event.getObject()).getProname();
		final int proprice= ((FileStoreBean) event.getObject()).getProprice();
		System.out.println("filename===== "+filename);
		System.out.println("filename===== "+proname);
		Connection con;
	
		Statement ps1;
		ResultSet rs;
		
	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","root");

			String query = "UPDATE shoppingcart.adminaddproducct" + 
					"SET filename='"+filename+"', mcategory='"+mcategory+"',subcategory='"+subcategory+"', proname='"+proname+"',proprice='"+proprice+"'" + 
					"WHERE filename=?";
			PreparedStatement pstmt = con.prepareStatement(query);;
			System.out.println("past =====" +pstmt);
			pstmt.setString(1, filename);
			 pstmt.executeUpdate();
			con.commit();
			  
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
       /* FacesMessage msg = new FacesMessage("Item Edited",((FileStoreBean) event.getObject()).getFilename());  
        FacesContext.getCurrentInstance().addMessage(null, msg); */ 
    }
	public void onCancel(RowEditEvent event) throws Exception { 
		final String filename=((FileStoreBean) event.getObject()).getFilename();
		System.out.println("filename===== "+filename);
	
		Connection con;
	
		Statement ps1;
		ResultSet rs;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart","root","root");

			String query = "delete from shoppingcart.adminaddproducct where filename=?";
			PreparedStatement pstmt = con.prepareStatement(query);;
			System.out.println("past =====" +pstmt);
			pstmt.setString(1, filename);
			 pstmt.executeUpdate();
			con.commit();
			  
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        FacesMessage msg = new FacesMessage("Item Cancelled");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
       /* orderList.remove((FileStoreBean) event.getObject());*/
    } 
}
