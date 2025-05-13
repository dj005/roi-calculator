package com.adobe.aem.demosite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables=Resource.class)
public class ROICalculatorV2Model {
	
	@ValueMapValue
	@Default(values="Calculator your ROI")
	protected String inputHeadline;
	
	@ValueMapValue
	@Default(values="User Id")
	protected String userId;
	
	@ValueMapValue
	@Default(values="Investment Amount")
	protected String investment;
	
	@ValueMapValue
	@Default(values="Revenue")
	protected String revenue;
	
	@ValueMapValue
	@Default(values="Expenses")
	protected String expenses;
	
	@ValueMapValue
	@Default(values="Calculated ROI")
	protected String outputHeadline;
	
	@ValueMapValue
	@Default(values="ROI")
	protected String roi;

	public String getInputHeadline() {
		return inputHeadline;
	}

	public String getUserId() {
		return userId;
	}

	public String getInvestment() {
		return investment;
	}

	public String getRevenue() {
		return revenue;
	}

	public String getExpenses() {
		return expenses;
	}

	public String getOutputHeadline() {
		return outputHeadline;
	}

	public String getRoi() {
		return roi;
	}

}
