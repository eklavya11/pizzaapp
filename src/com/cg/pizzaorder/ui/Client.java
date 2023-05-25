package com.cg.pizzaorder.ui;

import java.time.LocalDate;
import java.util.Scanner;

import com.cg.pizzaorder.bean.Customer;
import com.cg.pizzaorder.bean.PizzaOrder;
import com.cg.pizzaorder.exception.PizzaException;
import com.cg.pizzaorder.service.IPizzaOrderService;
import com.cg.pizzaorder.service.PizzaOrderService;

public class Client {
	public static void main(String[] args) {
		// create a objects of customer and pizzaorder
		Customer customer = new Customer();
		PizzaOrder pizza = new PizzaOrder();

		// instantiating service class
		IPizzaOrderService service = new PizzaOrderService();

		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to JustEat Pizza");
		System.out.println("Select one of the below options.");

		// will store main menu choice
		String menuChoice = "";

		// infinite loop until user exits its
		for (;;) {
			while (true) // to get main menu choice
			{
				System.out.println("1. Place Order\n2. Display order\n3. Exit");
				try {
					menuChoice = scan.next();
					boolean isValid = service.validateMenuChoice(menuChoice);
					if (isValid)
						break;
				} catch (PizzaException e) {
					System.out.println(e.getMessage());
				}
			}// end of main menu while loop

			if (menuChoice.equals("1"))// place order
			{
				while (true)// set customername
				{
					System.out
							.println("Enter Your First Name With 1st letter Capital");
					String name = scan.next();
					try {
						boolean isValid = service.validateAddress(name);
						if (isValid) {
							customer.setCustName(name);
							break;
						}
					} catch (PizzaException e) {
						System.out.println(e.getMessage());
					}
				}// end of customername

				while (true)// set address
				{
					System.out.println("Enter address");
					String address = scan.next();
					try {
						boolean isValid = service.validateName(address);
						if (isValid) {
							customer.setAddress(address);
							break;
						}
					} catch (PizzaException e) {
						System.exit(0);
					}
				}// end of address

				while (true)// set mobile number
				{
					System.out.println("Enter 10 digit mobile number");
					String mobileNumber = scan.next();
					try {
						boolean isValid = service.validateMobile(mobileNumber);
						if (isValid) {
							customer.setPhone(mobileNumber);
							break;
						}
					} catch (PizzaException e) {
						e.printStackTrace();
					}
				}// end of mobile number

				while (true)// select topping
				{
					System.out.println("Select a Toppings");
					System.out.println("Pizza Topping\tPrice");
					System.out.println("1. Capsicum\t30");
					System.out.println("2. Mushroom\t50");
					System.out.println("3. Jalapeno\t70");
					System.out.println("4. Paneer\t85");

					try {
						String toppingChoice = scan.next();
						// check if valid inputs
						boolean isValid = service
								.validateToppingChoice(toppingChoice);
						if (isValid) {
							switch (toppingChoice) {
							// set prices
							case "1":
								pizza.setToppingName("Capsicum");
								pizza.setTotalPrice(350 + 30);
							case "2":
								pizza.setToppingName("Mushroom");
								pizza.setTotalPrice(350 + 50);
							case "3":
								pizza.setToppingName("Jalapeno");
								pizza.setTotalPrice(350 + 70);
							case "4":
								pizza.setToppingName("Paneer");
								pizza.setTotalPrice(350 + 85);
							default:
								break;
							}
							break;
						}

					} catch (PizzaException e) {
						// if invalid inputs of toppings
						System.out.println(e.getMessage());
					}
				}// end of topping

				// order the pizza
				try {
					//sset current date
					pizza.setOrderDate(LocalDate.now());
					//get orderid if sucessfully order placed
					int orderID = service.placeOrder(customer, pizza);
					
					//print the result
					System.out
							.println("Pizza Order Successfully placed with Order id : "
									+ orderID);
					System.out.println("Order date : " + LocalDate.now());
					System.out.println("Total Bill : "+pizza.getTotalPrice());
				} catch (PizzaException e) {
					System.out.println(e.getMessage());
				}
			}// end of 1st main choice

			else if (menuChoice.equals("2"))// start of second choice
			{
				try {
					//get order id from user
					System.out.println("Enter Order ID");
					int orderID = scan.nextInt();
					//get data of order
					PizzaOrder getPizza = service.getOrderDetails(orderID);
					
					//print the result using toString method of PizzaOrder
					System.out.println("Your order details are as follows");
					System.out.println(getPizza);
				} catch (PizzaException e) {
					System.out.println(e.getMessage());
				} catch (NumberFormatException e) {
					System.out.println("Enter valid Number");
				}
			}// end of second choice
			else // exit
			{
				System.out.println("Thank You. Visit Again.");
				break;
			}
		}// end of for loop
	}// end of main method
}
