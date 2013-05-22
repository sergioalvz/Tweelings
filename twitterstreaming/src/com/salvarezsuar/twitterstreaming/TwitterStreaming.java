package com.salvarezsuar.twitterstreaming;

import java.io.FileWriter;
import java.io.IOException;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import au.com.bytecode.opencsv.CSVWriter;

public class TwitterStreaming {

	private String path;
	private String[] tracks;

	private CSVWriter writer;
	private TwitterStream twitter;

	public TwitterStreaming(String path, String[] tracks) {
		this.path = path;
		this.tracks = tracks;
	}

	public void open() throws IOException {
		_openCSVFile();
		_openTwitterStream();
	}

	private void _openCSVFile() throws IOException {
		writer = new CSVWriter(new FileWriter(path));

	}

	private void _openTwitterStream() {
		twitter = new TwitterStreamFactory().getInstance();
		AccessToken oAuth = new AccessToken("",
				"");
		twitter.setOAuthConsumer("", "");
		twitter.setOAuthAccessToken(oAuth);
		twitter.addListener(new MyStatusListener(writer));
	}

	public void run() {
		twitter.filter(new FilterQuery().track(tracks));
	}

	public void close() throws IOException {
		writer.close();
		twitter.shutdown();
	}
}
