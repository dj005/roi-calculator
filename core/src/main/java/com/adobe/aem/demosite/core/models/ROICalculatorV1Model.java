package com.adobe.aem.demosite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables=Resource.class)
public class ROICalculatorV1Model {
	
	@ValueMapValue
	@Default(values="Calculator your ROI")
	protected String inputHeadline;
	
	@ValueMapValue
	@Default(values="Number of employees")
	protected String employees;
	
	@ValueMapValue
	@Default(values="Annual Revenue")
	protected String revenue;
	
	@ValueMapValue
	@Default(values="Average Ticket Resolution Time (Hrs)")
	protected String ttr;
	
	@ValueMapValue
	@Default(values="Annual Ticket Volume")
	protected String tickets;
	
	@ValueMapValue
	@Default(values="Cost per Ticket")
	protected String cpt;
	
	@ValueMapValue
	@Default(values="Year 1 Ticket Impact")
	protected String outputHeadline;
	
	@ValueMapValue
	@Default(values="Tickets Resolved")
	protected String resolved;
	
	@ValueMapValue
	@Default(values="Tickets Accelerated")
	protected String accelerated;
	
	@ValueMapValue
	@Default(values="Productive Hours Saved per Employee")
	protected String productive;
	
	@ValueMapValue
	@Default(values="Total Wait Hours Saved per Employee")
	protected String wait;

	public String getInputHeadline() {
		return inputHeadline;
	}

	public String getEmployees() {
		return employees;
	}

	public String getRevenue() {
		return revenue;
	}

	public String getTtr() {
		return ttr;
	}

	public String getTickets() {
		return tickets;
	}

	public String getCpt() {
		return cpt;
	}

	public String getOutputHeadline() {
		return outputHeadline;
	}

	public String getResolved() {
		return resolved;
	}

	public String getAccelerated() {
		return accelerated;
	}

	public String getProductive() {
		return productive;
	}

	public String getWait() {
		return wait;
	}

}
