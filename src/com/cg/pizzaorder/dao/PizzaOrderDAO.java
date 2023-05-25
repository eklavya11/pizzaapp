package com.cg.pizzaorder.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.cg.pizzaorder.bean.Customer;
import com.cg.pizzaorder.bean.PizzaOrder;
import com.cg.pizzaorder.exception.PizzaException;

public class PizzaOrderDAO implements IPizzaOrderDAO {

	// declaring maps to store datas of user and pizza
	Map<Integer, PizzaOrder> pizzaEntry = new HashMap<>();
	Map<Integer, Customer> customerEntry =  new HashMap<>();

	public PizzaOrderDAO() {
		
	}

	// add data to map
	@Override
	public int placeOrder(Customer customer, PizzaOrder pizza)
			throws PizzaException {

		try {
			// create random customer ID
			int custID = (int) (Math.random() * 100);

			// create random pizza ID
			int orderID = (int) (Math.random() * 1000);

			customer.setCustomerId(custID);
			pizza.setCustomerId(custID);
			pizza.setOrderId(orderID);

			// passing ids as keys to bith the maps
			customerEntry.put(custID, customer);
			pizzaEntry.put(orderID, pizza);
            Connection c = null;
			 try {
		         Class.forName("org.postgresql.Driver");
		          c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizza","postgres", "capg1234");
		          String query = "INSERT INTO orders" +
		        	        "  (orderid, custid, toppings, price) VALUES " +
		        	        " (?, ?, ?, ?);";
		          PreparedStatement preparedStatement = c.prepareStatement(query);
		              preparedStatement.setInt(1, orderID);
		              preparedStatement.setInt(2, custID);
		              preparedStatement.setString(3, pizza.getToppingName());
		              preparedStatement.setInt(4, (int)pizza.getTotalPrice());
		              preparedStatement.executeUpdate();
		          
		      } 
			 catch (SQLException e) {

		            // print SQL exception information
		            System.out.println(e.getMessage());
		        }
			 catch (Exception e) {
		         e.printStackTrace();
		         System.err.println(e.getClass().getName()+": "+e.getMessage());
		         System.exit(0);
		      }
			// return order id that was generated
			return orderID;
		} catch (Exception e) {
			//if cannot create customer and pizza entry
			System.out.println(e.getMessage());
			throw new PizzaException("Cannot create order. Try again later");
		}
	}

	//to fetch 
	@Override
	public PizzaOrder getOrderDetails(int orderId) throws PizzaException {
		
		// Get order from user
		PizzaOrder pizza = new PizzaOrder();
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
	          c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pizza","postgres", "capg1234");
	          String query = "SELECT * FROM orders WHERE orderid = ?";
	          PreparedStatement st = c.prepareStatement(query);
	          st.setInt(1, orderId);
	          ResultSet rs = st.executeQuery();
	          rs.next();
	        	  int oi = rs.getInt("orderid");
	        	  int ci = rs.getInt("custid");
	        	  String tpng = rs.getString("toppings");
	        	  int p = rs.getInt("price");
	        	  pizza.setOrderId(oi);
	        	  pizza.setCustomerId(ci);
	        	  pizza.setToppingName(tpng);
	        	  pizza.setTotalPrice(p);
	        	  return pizza;
	          
	          
		}
		catch (SQLException e) {

            // print SQL exception information
            System.out.println(e.getMessage());
        }
		catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
		
		
		//if valid then pass the pizzaorder object
		
			//if order id doesnt exits
			throw new PizzaException("No Order Found with this ID");
	}

}
