package com.cg.pizzaorder.bean;

import java.time.LocalDate;

public class PizzaOrder {

	//declare fields
	private int orderId;
	private int customerId;
	private double totalPrice;

	//custom fields for more accurate results
	private String toppingName;
	private LocalDate orderDate;
	
	//getters and setters
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getToppingName() {
		return toppingName;
	}
	public void setToppingName(String toppingName) {
		this.toppingName = toppingName;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	//to display the message when user fetches order
	@Override
	public String toString() {
		return "Order id :" +orderId+"\nCustomer id : "+customerId+"\nToppings : "+toppingName+"\nTotal price : "+totalPrice+"\n";
	}
	
	
}
