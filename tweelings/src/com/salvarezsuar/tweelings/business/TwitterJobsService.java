package com.salvarezsuar.tweelings.business;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import com.salvarezsuar.tweelings.model.Trend;
import com.salvarezsuar.tweelings.model.TwitterJob;
import com.salvarezsuar.tweelings.model.types.TwitterJobType;
import com.salvarezsuar.tweelings.persistence.TwitterJobsDataService;

public class TwitterJobsService {
	public Vector<TwitterJob> getTwitterJobs(TwitterJobType type) throws Exception {
		return new TwitterJobsDataService().getTwitterJobs(type);
	}

	public TwitterJob getTwitterJob(Long jobId) throws SQLException {
		return new TwitterJobsDataService().getTwitterJob(jobId);
	}

	public Set<Trend> getJobResults(TwitterJob job) throws IOException {
		Set<Trend> results = new TreeSet<Trend>();
		Properties properties = new Properties();
		properties.load(TwitterJobsService.class.getResourceAsStream("/results/" + job.getFile()));
		for (Object date : properties.keySet()) {
			Trend trend = new Trend();
			String value = properties.getProperty(date.toString());
			trend.setDate(date.toString());
			trend.setValue(Float.valueOf(value));
			results.add(trend);
		}

		return results;
	}
}
