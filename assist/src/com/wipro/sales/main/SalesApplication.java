package com.wipro.sales.main;
import java.util.*;
import com.wipro.sales.service.*;
import com.wipro.sales.bean.*;
public class SalesApplication {
 public static void main(String args[]) throws Exception
 {	 
	 int num=0; 
	 Scanner s=new Scanner(System.in);
	 while(num!=5)
	 {	
	     administrator ad=new administrator(); 
		 System.out.println("Choose any one");
		 System.out.println("1. Insert Stock");
		 System.out.println("2. Delete Stock");
		 System.out.println("3. Insert Sales");
		 System.out.println("4. View Sales report");
		 System.out.println("5. Exit");
		 num=s.nextInt();
		 if(num==1)
		 {	
			 product obj=new product();
			 System.out.println("Product Name");
			 obj.setProductName(s.next());
			 System.out.println("Total Quantity");
			 obj.setQuantityOnHand(s.nextInt());
			 System.out.println("Product Unit Price");
			 obj.setProductUnitPrice(s.nextDouble());
			 System.out.println(ad.insertStock(obj));
		 }
		 else if(num==2)
		 {
			 System.out.println("Enter product ID");
			 System.out.println(ad.deleteStock(s.next()));
			 
		 }
		 else if(num==3)
		 {
			 sales obj=new sales();
			 System.out.println("Enter product ID");
			 obj.setProductId(s.next());
			 
			 System.out.println("Total Quantity sold");
			 obj.setQuantitySold(s.nextInt());
//			 System.out.println("Product Unit Price");
//			 obj.setSalesPricePerUnit(s.nextDouble());
			 System.out.println("Sales unit price");
			 obj.setSalesPricePerUnit(s.nextDouble());
			 System.out.println("input date in format dd/MM/yyyy");
			 String str=s.next();
			 java.util.Date d=new java.text.SimpleDateFormat("dd/MM/yyyy").parse(str);
			 obj.setSalesDate(d);
			 System.out.println(ad.insertSales(obj));
			 
		 }
		 else if(num==4)
		 {
			  ArrayList<SalesReport> list=new ArrayList<SalesReport>();
			  list=ad.getSalesReport();
			  for(int i=0;i<list.size();i++)
			  {
				  System.out.print(list.get(i).getSalesID()+"   ");
				  System.out.print(list.get(i).getSalesDate()+"   ");
				  System.out.print(list.get(i).getProductId()+"   ");
				  System.out.print(list.get(i).getProductName()+"   ");
				  System.out.print(list.get(i).getQuantitySold()+"   ");
				  System.out.print(list.get(i).getProductUnitPrice()+"   ");
				  System.out.print(list.get(i).getSalesPricePerUnit()+"   ");
				  System.out.print(list.get(i).getProfitAmount()+"   ");
				  System.out.println();
			  }
			  
		 }
		 else if(num==5)
		 break;
		 else 
		 {
			 System.out.print("choose from 1-4");
		 }
	 }
 }
}
