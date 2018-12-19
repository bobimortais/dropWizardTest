package com.robson.drop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bill 
{
	private int id;
	
	private String type;
	
	private double value;
	
	private String description;
	
	private int companyId;

	public Bill()
	{
		
	}
	
	public Bill(int id, String type, double value, String description, int companyID)
	{
		this.id = id;
		this.type = type;
		this.value = value;
		this.description = description;
		this.companyId = companyID;
	}
	
	@JsonProperty
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@JsonProperty
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
}
