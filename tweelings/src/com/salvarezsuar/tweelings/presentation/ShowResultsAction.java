package com.salvarezsuar.tweelings.presentation;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.salvarezsuar.tweelings.business.TwitterJobsService;
import com.salvarezsuar.tweelings.model.Trend;
import com.salvarezsuar.tweelings.model.TwitterJob;

public class ShowResultsAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 7828505275980296190L;

	private Long jobId;
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {
		TwitterJobsService service = new TwitterJobsService();
		TwitterJob job = service.getTwitterJob(jobId);
		Set<Trend> trends = service.getJobResults(job);
		request.setAttribute("job", job);
		request.setAttribute("trends", trends);

		return ActionSupport.SUCCESS;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
