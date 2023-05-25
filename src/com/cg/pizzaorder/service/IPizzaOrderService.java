package com.cg.pizzaorder.service;

import com.cg.pizzaorder.bean.Customer;
import com.cg.pizzaorder.bean.PizzaOrder;
import com.cg.pizzaorder.exception.PizzaException;

public interface IPizzaOrderService {

	// regex to check inputs
	String menuChoice = "[1-3]{1}";
	String toppingChoice = "[1-4]{1}";
	String userName = "[A-Z][a-z]{2,20}";
	String address = "[a-zA-z0-9]{2,20}";
	String usermobilePattern = ("(0/91)?[7-9][0-9]{9}");

	// to add order
	public int placeOrder(Customer customer, PizzaOrder pizza)
			throws PizzaException;

	// to get order
	public PizzaOrder getOrderDetails(int orderId) throws PizzaException;

	// to validate inputs
	public boolean validateMenuChoice(String menuChoice) throws PizzaException;

	public boolean validateToppingChoice(String toppingChoice)
			throws PizzaException;

	public boolean validateName(String name) throws PizzaException;

	public boolean validateAddress(String address) throws PizzaException;

	public boolean validateMobile(String mobileNumber) throws PizzaException;

}
