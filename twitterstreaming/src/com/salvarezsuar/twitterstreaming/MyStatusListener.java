package com.salvarezsuar.twitterstreaming;

import au.com.bytecode.opencsv.CSVWriter;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class MyStatusListener implements StatusListener {

	CSVWriter writer;

	public MyStatusListener(CSVWriter writer) {
		this.writer = writer;
	}

	public void onStatus(Status status) {
		if (status.getUser().getLang().equalsIgnoreCase("es")) {
			String[] line = { status.getUser().getLang(), status.getUser().getName(), status.getCreatedAt().toString(),
					status.getText() };
			writer.writeNext(line);
		}
	}

	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}

	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}

	public void onException(Exception ex) {
		ex.printStackTrace();
	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {}

	@Override
	public void onStallWarning(StallWarning arg0) {}

}
