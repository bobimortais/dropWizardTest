package com.robson.drop.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "BILL")
@Table
public class Bill implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bill_id")
	private int id;
	
	@Column(name = "bill_type")
	private String type;
	
	@Column(name = "bill_value")
	private double value;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "company_id")
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
