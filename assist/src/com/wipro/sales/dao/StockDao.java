package com.wipro.sales.dao;
import java.sql.*;
import com.wipro.sales.bean.*;
import com.wipro.sales.unit.DButil;
public class StockDao {
 public int insertStock(product sales) throws Exception
 {  
	    int t=0;
		PreparedStatement ps;
		Connection con=DButil.getDBConnection();
		ps=con.prepareStatement("insert into TBL_stock values (?,?,?,?,?)");
		ps.setString(1,sales.getProductID());
		ps.setString(2,sales.getProductName());
		ps.setDouble(3,sales.getQuantityOnHand());
		ps.setDouble(4,sales.getProductUnitPrice());
		ps.setInt(5,sales.getReorderLevel());
		t=ps.executeUpdate();
	return t; 
 }
 //-----------------------------------------------------------------------------------------
 public String generateProductId(String name) throws Exception
	{	String y=name.substring(0,2);
	 	PreparedStatement ps;
		Connection con=DButil.getDBConnection();
		ps=con.prepareStatement("select stoct from seq");
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			y+=Integer.toString(rs.getInt(1));
			ps=con.prepareStatement("update seq set stoct=?");
			ps.setString(1,Integer.toString(rs.getInt(1)+1));
			ps.executeUpdate();
		}
//		System.out.print(y);
		return y;
	}
 //------------------------------------------------------------------------------------------
 public int updateStock(String productID,int soldQty)
    {	PreparedStatement ps;
		Connection con=DButil.getDBConnection();
    	int t=0;
    	
    	try
    	{
    		ps=con.prepareStatement("select * from tbl_stock where product_Id=?");
    		ps.setString(1,productID);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		int val=rs.getInt("Quantity_on_hand")-soldQty;
    		ps=con.prepareStatement("update tbl_stock set quantity_on_hand=? where product_Id=?");
    		ps.setString(1,Integer.toString(val));
    		ps.setString(2,productID);
    		t=ps.executeUpdate();
    	}
    	catch(Exception e)
    	{
    		System.out.print(e);
    	}
    
    	return t;
    }
    //---------------------------------------------------------------------------------------------
    public product getStock(String productID) 
    {	
    	PreparedStatement ps;
		Connection con=DButil.getDBConnection();
		product prod=new product();
		
    	try
    	{
    		ps=con.prepareStatement("delete from tbl_stock where product_Id=?");
    		ps.setString(1,productID);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			prod.setProductID(rs.getString(1));
    			prod.setProductName(rs.getString(2));
    			prod.setQuantityOnHand(rs.getInt(3));
    			prod.setProductUnitPrice(rs.getDouble(4));
    			prod.setReorderLevel(rs.getInt(5));
    			
    		}
    	}
    	catch(Exception e)
    	{	
    		System.out.print(e);
    	}
    	return prod;
    }
    //----------------------------------------------------------------------------------------------
    public int deleteStock(String productID)
    {	int t=0;
    	PreparedStatement ps;
		Connection con=DButil.getDBConnection();
    	try
    	{
    		ps=con.prepareStatement("delete from tbl_stock where product_Id=?");
    		ps.setString(1,productID);
    		t=ps.executeUpdate();
    	}
    	catch(Exception e)
    	{
    		System.out.print(e);
    	}
    	return t;
    }
}
