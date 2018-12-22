package com.robson.drop.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.codahale.metrics.annotation.Timed;
import com.robson.drop.entities.Bill;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class BillsInfoResource {
    private final String template;
    private final String defaultName;
    
    private static SessionFactory factory; 

    public BillsInfoResource(String template, String defaultName)
    {
        this.template = template;
        this.defaultName = defaultName;
    }

    @Path("/getBills")
    @GET
    @Timed
    public List<Bill> getBills(@QueryParam("type") Optional<String> type) 
    {
    	List<Bill> billList =  new ArrayList<Bill>();
    	Bill bill1 = new Bill(1, "Utility", 20.25, "Gas", 1);
    	Bill bill2 = new Bill(2, "Utility", 12.03, "Water", 2);
    	Bill bill3 = new Bill(3, "Food", 5, "Pizza", 3);
    	Bill bill4 = new Bill(4, "Fun", 10, "Football Ticket", 4);
    	Bill bill5 = new Bill(5, "Doctor", 250, "Broken Nose", 5);
    	billList.add(bill1);
    	billList.add(bill2);
    	billList.add(bill3);
    	billList.add(bill4);
    	billList.add(bill5);
        return billList;
    }
    
    @Path("/getAllBills")
    @GET
    @Timed
    public List<Bill> getAllBills(@QueryParam("type") Optional<String> type) 
    {
    	List<Bill> billList =  getBillsFromDB();
        return billList;
    }

    private List<Bill> getBillsFromDB()
    {
    	List<Bill> billList = new ArrayList<Bill>();
		try 
		{
			
			Configuration configObj = new Configuration();
			configObj.configure("hibernate.cfg.xml");
			
			ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
			
			factory = configObj.buildSessionFactory(serviceRegistryObj);
			Session session = factory.openSession();
			Transaction tx = null;

			try 
			{
				tx = session.beginTransaction();
				billList = session.createQuery("FROM BILL").list();
				tx.commit();
			} 
			catch (HibernateException e) 
			{
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			}
			finally 
			{
				session.close();
			}
		} 
		catch (Throwable ex) 
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		return billList;
    }
}
