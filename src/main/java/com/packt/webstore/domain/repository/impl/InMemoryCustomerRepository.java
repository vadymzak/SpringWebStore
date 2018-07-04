package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;


@Repository
@Transactional(readOnly = true)
public class InMemoryCustomerRepository implements CustomerRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly = true)
	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		/*Customer ob = new Customer();
		ob.setCustomerId("9");
		ob.setName("LALA");
		ob.setAddress("sdfsdfsdf sdfsdf");
		//ob.setNoOfOrdersMade(true);
		session.save(ob);
		session.getTransaction().commit();*/
		String hql = "FROM Customer";
		Query query = session.createQuery(hql);
		List<Customer> result  = query.list();
		session.close();
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String,Object>();
		/*List<Customer> result = jdbcTemplate.query("SELECT * FROM customer", params, new RowMapper<Customer>() {
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getString("ID"));
				customer.setName(rs.getString("NAME"));
				customer.setAddress(rs.getString("ADDRESS"));
				//customer.setNoOfOrdersMade(rs.getBoolean("NOOFORDERSMADE"));
				  customer.setNoOfOrdersMade(rs.getBoolean("NOOFORDERSMADE"));
				return customer;
			}
		});*/
				// new CustomerMapper());
		return result;
	}
	
	private static final class CustomerMapper implements RowMapper<Customer> {
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getString("ID"));
			customer.setName(rs.getString("NAME"));
			customer.setAddress(rs.getString("ADDRESS"));
			//customer.setNoOfOrdersMade(rs.getString("NOOFORDERSMADE"));
			return customer;
		}
	}
	@Transactional(readOnly = false)
	@Override
	public void addCustomer(Customer newCustomer) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(newCustomer);
		session.getTransaction().commit();
	}
	

}
