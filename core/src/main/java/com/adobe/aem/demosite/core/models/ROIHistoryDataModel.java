package com.adobe.aem.demosite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables=Resource.class)
public class ROIHistoryDataModel {
	
	public String getExpenses() {
		return expenses;
	}

	public String getInvestment() {
		return investment;
	}

	public String getRevenue() {
		return revenue;
	}

	public String getRoi() {
		return roi;
	}

	public String getUserId() {
		return userId;
	}

	@ValueMapValue
	@Optional
	protected String expenses;
	
	@ValueMapValue
	@Optional
	protected String investment;
	
	@ValueMapValue
	@Optional
	protected String revenue;
	
	@ValueMapValue
	@Optional
	protected String roi;
	
	@ValueMapValue
	@Optional
	protected String userId;
	
	
}
