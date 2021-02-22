package com.wipro.sales.service;
import java.sql.*;
import java.util.*;
import com.wipro.sales.bean.*;
import com.wipro.sales.dao.*;
import com.wipro.sales.unit.*;
public class administrator {
	public String insertStock(product prod) throws Exception
	{
		if(prod==null||prod.getProductName().length()<2)
			return "Data not valid for insertion";
		StockDao sd=new StockDao();
		String result=sd.generateProductId(prod.getProductName());
		if(result!=null) 
		{
			prod.setProductID(result);
			sd.insertStock(prod);
			
		}
		else return "Data not valid for insertion";
		
			
		return prod.getProductID();
	}
//------------------------------------------------------------------------------------------
	public String deleteStock(String productId) throws Exception
	{	StockDao sd=new StockDao();
		int res=sd.deleteStock(productId);
		if(res==0)return "record cannot be deleted";
		
		return "record Deleted";
	}
//-------------------------------------------------------------------------------------
	public String insertSales(sales salesobj) throws Exception
	{	PreparedStatement ps;
		Connection con=DButil.getDBConnection();
		SalesDao sd=new SalesDao();
		StockDao std=new StockDao();
		//-----------
		if(salesobj==null)
		return "Object not valid for insertion";
		ps=con.prepareStatement("Select product_Id from tbl_stock where product_id=?");
		ps.setString(1,salesobj.getProductID());
		if(ps.executeQuery()==null)return "Unknown product for sales";
		//----------
		ps=con.prepareStatement("select quantity_on_hand from tbl_stock where product_ID=?");
		ps.setString(1,salesobj.getProductID());
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		if(rs.getInt("Quantity_On_Hand")<salesobj.getQuantitySold())
			return "Not enough stock on hand for sales";
		//-----------
		java.sql.Date date=new java.sql.Date(salesobj.getSalesDate().getTime());
		java.sql.Date currdate=new java.sql.Date(new java.util.Date().getTime());
		if(currdate.compareTo(date)<0)return "Invalid date";
		//-----------
		String result=sd.generateSalesId(salesobj.getSalesDate());
		salesobj.setSalesID(result);
		int flag=sd.insertSales(salesobj);
		if(flag!=0)
		{
			flag=std.updateStock(salesobj.getProductID(),salesobj.getQuantitySold());
			if(flag!=0)return "Sales completed";
		}
		
		return "Error";
	}
//------------------------------------------------------------------------------------------
	public ArrayList<SalesReport> getSalesReport()
	{	SalesDao sd=new SalesDao();
		return sd.getSalesReport();
	}
}
