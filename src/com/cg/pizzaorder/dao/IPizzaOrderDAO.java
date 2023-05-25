package com.cg.pizzaorder.dao;

import com.cg.pizzaorder.bean.Customer;
import com.cg.pizzaorder.bean.PizzaOrder;
import com.cg.pizzaorder.exception.PizzaException;

public interface IPizzaOrderDAO {

	// to add order
	public int placeOrder(Customer customer, PizzaOrder pizza)
			throws PizzaException;

	// to get order
	public PizzaOrder getOrderDetails(int orderId) throws PizzaException;
}
