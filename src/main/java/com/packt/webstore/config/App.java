package com.packt.webstore.config;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.packt.webstore.domain.Customer;


public class App 
{
    public static void main( String[] args )
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
      
        Customer ob = new Customer();
        
       
        ob.setCustomerId("15");
		ob.setName("LALA");
		ob.setAddress("sdfsdfsdf sdfsdf");
		session.save(ob);
		session.getTransaction().commit();
        
       // String hql = "FROM Customer";
	//	Query query = session.createQuery(hql);
	//	List<Customer> result  = query.list();
        //student.setStudentId(15);
        //session.save(student);
        //session.getTransaction().commit();
        System.out.println("Great! All saved");
        
        //session.delete(student);
        //session.update(student);
        //session.getTransaction().commit();
        //System.out.println("Great! Student was deleted");
    }
}
