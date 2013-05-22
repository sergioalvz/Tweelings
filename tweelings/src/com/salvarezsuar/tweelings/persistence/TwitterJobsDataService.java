package com.salvarezsuar.tweelings.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.salvarezsuar.tweelings.model.TwitterJob;
import com.salvarezsuar.tweelings.model.types.TwitterJobType;
import com.salvarezsuar.tweelings.util.JDBC;

public class TwitterJobsDataService {

	public Vector<TwitterJob> getTwitterJobs(TwitterJobType type) throws SQLException {
		Vector<TwitterJob> jobs = new Vector<TwitterJob>();

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			con = JDBC.getConnection();
			st = con.prepareStatement("SELECT * FROM TJobs WHERE jobType = ?");
			st.setString(1, type.toString());
			rs = st.executeQuery();

			while (rs.next()) {
				TwitterJob job = new TwitterJob();
				job.setDescription(rs.getString("description"));
				job.setFile(rs.getString("path"));
				job.setToken(rs.getString("token"));
				job.setId(rs.getLong("id"));
				job.setType(TwitterJobType.valueOf(rs.getString("jobType")));
				job.setObservations(rs.getString("observations"));

				jobs.add(job);
			}

		} finally {
			st.close();
			con.close();
		}
		return jobs;
	}

	public TwitterJob getTwitterJob(Long jobId) throws SQLException {

		TwitterJob job = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			con = JDBC.getConnection();
			st = con.prepareStatement("SELECT * FROM TJobs WHERE id = ?");
			st.setLong(1, jobId);
			rs = st.executeQuery();
			if (rs.first()) {
				job = new TwitterJob();
				job.setDescription(rs.getString("description"));
				job.setFile(rs.getString("path"));
				job.setToken(rs.getString("token"));
				job.setId(rs.getLong("id"));
				job.setType(TwitterJobType.valueOf(rs.getString("jobType")));
				job.setObservations(rs.getString("observations"));
			}
		} finally {
			st.close();
			con.close();
		}

		return job;
	}
}
