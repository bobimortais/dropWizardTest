package com.robson.drop.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.robson.drop.entities.Bill;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class BillsInfoResource {
    private final String template;
    private final String defaultName;

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
    	List<Bill> billList =  new ArrayList<Bill>();
    	Bill bill1 = new Bill(1, "Utility", 20.25, "Gas", 1);
    	Bill bill2 = new Bill(2, "Utility", 12.03, "Water", 2);
    	billList.add(bill1);
    	billList.add(bill2);
        return billList;
    }

}
