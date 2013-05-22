package com.salvarezsuar.tweelings.presentation;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;

import com.opensymphony.xwork2.ActionSupport;
import com.salvarezsuar.tweelings.business.TwitterJobsService;
import com.salvarezsuar.tweelings.model.types.TwitterJobType;

public class ShowHomePageAction extends ActionSupport implements ApplicationAware {

	private static final long serialVersionUID = -1848468958141424014L;
	private Map<String, Object> application;

	@Override
	public String execute() throws Exception {
		TwitterJobsService service = new TwitterJobsService();
		application.put("jobsF1", service.getTwitterJobs(TwitterJobType.F1));
		application.put("jobsFootball", service.getTwitterJobs(TwitterJobType.FOOTBALL));
		application.put("jobsTennis", service.getTwitterJobs(TwitterJobType.TENNIS));

		return ActionSupport.SUCCESS;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

}
