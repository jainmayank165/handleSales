package com.wipro.sales.bean;

public class sales {

	String salesID;
	int quantitySold;
	java.util.Date salesDate;
	String productId;
	double salesPricePerUnit;
	//---------------------------------------------------------------------------
    public String getSalesID() 
    {
		return salesID;
	}
	public void setSalesID(String salesID) 
	{
		this.salesID = salesID;
	}
	public java.util.Date getSalesDate() 
	{
		return salesDate;
	}
	public void setSalesDate(java.util.Date salesDate) 
	{
		this.salesDate = salesDate;
	}
	
	public String getProductID() 
	{
		return productId;
	}
	public void setProductId(String productId) 
	{
		this.productId = productId;
	}
	
	public int getQuantitySold() 
	{
		return quantitySold;
	}
	public void setQuantitySold(int quantitySold)
	{
		this.quantitySold = quantitySold;
	}
	
	public double getSalesPricePerUnit()
	{
		return salesPricePerUnit;
	}
	public void setSalesPricePerUnit(double salesPricePerUnit)
	{
		this.salesPricePerUnit = salesPricePerUnit;
	}
}
