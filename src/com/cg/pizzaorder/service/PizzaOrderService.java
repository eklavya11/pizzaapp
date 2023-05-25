package com.cg.pizzaorder.service;

import com.cg.pizzaorder.bean.Customer;
import com.cg.pizzaorder.bean.PizzaOrder;
import com.cg.pizzaorder.dao.IPizzaOrderDAO;
import com.cg.pizzaorder.dao.PizzaOrderDAO;
import com.cg.pizzaorder.exception.PizzaException;

public class PizzaOrderService implements IPizzaOrderService {

	// create a dao interface object
	IPizzaOrderDAO dao;

	// initilize in constructor
	public PizzaOrderService() {
		dao = new PizzaOrderDAO();
	}

	// place order
	// pass data to dao class
	@Override
	public int placeOrder(Customer customer, PizzaOrder pizza)
			throws PizzaException {
		try {
			return dao.placeOrder(customer, pizza);
		} catch (Exception e) {
			// throw message from dao to ui
			throw new PizzaException(e.getMessage());
		}
	}

	// get order
	// pass data to dao class
	@Override
	public PizzaOrder getOrderDetails(int orderId) throws PizzaException {
		try {
			return dao.getOrderDetails(orderId);
		} catch (Exception e) {
			// throw message from dao to ui if order id not found
			throw new PizzaException(e.getMessage());
		}
	}

	// validation methods
	// will throw exception if inputs are invalids
	@Override
	public boolean validateMenuChoice(String menuChoice) throws PizzaException {
		if (menuChoice.matches(PizzaOrderService.menuChoice))
			return true;
		else
			throw new PizzaException("Please Select Between 1 to 3.");
	}

	@Override
	public boolean validateToppingChoice(String toppingChoice)
			throws PizzaException {
		if (toppingChoice.matches(PizzaOrderService.toppingChoice))
			return true;
		else
			throw new PizzaException("Please Select Between 1 to 4.");
	}

	@Override
	public boolean validateName(String name) throws PizzaException {
		if (name.matches(PizzaOrderService.userName))
			return true;
		else
			throw new PizzaException(
					"Please Enter only first name with 1st letter capital");
	}

	@Override
	public boolean validateAddress(String address) throws PizzaException {
		if (address.matches(PizzaOrderService.address))
			return true;
		else
			throw new PizzaException("Please Select only 1 Location name");
	}

	@Override
	public boolean validateMobile(String mobileNumber) throws PizzaException {
		if (mobileNumber.matches(PizzaOrderService.usermobilePattern))
			return true;
		else
			throw new PizzaException(
					"Please enter 10 digit number starting with 7,8 or 9");
	}

}
