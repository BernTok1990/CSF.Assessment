package vttp2022.assessment.csf.orderbackend.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;

@Service
public class OrderService {

	private static final String SQL_CREATE_ORDER = "insert into orders (name, email, pizza_size, thick_crust, sauce, toppings, comments) values(?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_GET_BY_EMAIL = "select * from orders where email = ? ";

    @Autowired
    private JdbcTemplate template; 

	// @Autowired
	// private PricingService priceSvc;

	// POST /api/order
	// Create a new order by inserting into orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature
	public void createOrder(Order order) {
		template.update(SQL_CREATE_ORDER);
			
	}

	// GET /api/order/<email>/all
	// Get a list of orders for email from orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature
	public List<OrderSummary> getOrdersByEmail(String email) {
		// Use priceSvc to calculate the total cost of an order
		List<OrderSummary> summaries = new LinkedList<>();

		SqlRowSet rs = template.queryForRowSet(SQL_GET_BY_EMAIL, email);
		while (rs.next()){
			OrderSummary summary = OrderSummary.create(rs);
			summaries.add(summary);
		}
		return getOrdersByEmail(email);
	}
}
