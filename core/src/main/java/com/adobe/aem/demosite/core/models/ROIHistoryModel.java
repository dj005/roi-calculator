package com.adobe.aem.demosite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables=Resource.class)
public class ROIHistoryModel {
	
	@ValueMapValue
	@Default(values="ROI History")
	protected String headline;
	
	@ValueMapValue
	@Default(values="Enter User Id")
	protected String userId;
	


	public String getheadline() {
		return headline;
	}

	public String getUserId() {
		return userId;
	}
}
