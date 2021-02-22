package com.wipro.sales.dao;
import java.sql.*;
import java.util.*;

import com.wipro.sales.bean.*;
import com.wipro.sales.unit.DButil;
public class SalesDao {
	public int insertSales(sales sales)
	{	int t=0;
		PreparedStatement ps;
		Connection con=DButil.getDBConnection();
		try
		{   
			java.sql.Date date=new java.sql.Date(sales.getSalesDate().getTime());
			ps=con.prepareStatement("insert into TBL_SALES values (?,?,?,?,?)");
			ps.setString(1,sales.getSalesID());
			ps.setDate(2,date);
			ps.setString(3,sales.getProductID());
			ps.setInt(4,sales.getQuantitySold());
			ps.setDouble(5,sales.getSalesPricePerUnit()); 
			t=ps.executeUpdate();
		}
		catch(Exception e){
			System.out.print(e);
		}
		return t;
	}
	//--------------------------------------------------------------------------------
	public String generateSalesId(java.util.Date sales) throws Exception
	{	
		String y=Integer.toString((int)(sales.getYear()+1900)).substring(2,4);
		PreparedStatement ps;
		Connection con=DButil.getDBConnection();
		ps=con.prepareStatement("select sales from seq");
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			y+=Integer.toString(rs.getInt(1));
			String str="update seq set sales="+Integer.toString(rs.getInt(1)+1);
			//System.out.println(str);
			ps=con.prepareStatement(str);
			ps.executeUpdate();
		}
		//System.out.println(y);
		return y;
	}
	//--------------------------------------------------------------------
	public ArrayList<SalesReport> getSalesReport()
	{
		ArrayList<SalesReport> sr=new ArrayList<SalesReport>();
		PreparedStatement ps;
		Connection con=DButil.getDBConnection();
		try
		{
			ps=con.prepareStatement("select * from v_Sales_report");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				SalesReport sale=new SalesReport();
				sale.setSalesID(rs.getString(1));
				sale.setSalesDate(rs.getDate(2));
				sale.setProductId(rs.getString(3));
				sale.setProductName(rs.getString(4));
				sale.setQuantitySold(rs.getInt(5));
				sale.setProductUnitPrice(rs.getDouble(6));
				sale.setSalesPricePerUnit(rs.getDouble(7));
				sale.setProfitAmount(rs.getDouble(8));
				sr.add(sale);
			}
		}
		catch(Exception e) {
			System.out.print(e);
		}
		return sr;
		
	}
	
}
