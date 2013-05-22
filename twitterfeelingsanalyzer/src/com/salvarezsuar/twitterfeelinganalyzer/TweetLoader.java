package com.salvarezsuar.twitterfeelinganalyzer;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

import com.salvarezsuar.twitterfeelinganalyzer.model.Tweet;

public class TweetLoader {

	CSVReader reader;
	List<Tweet> tweets;

	public void load(String file) throws IOException, ParseException {
		reader = new CSVReader(new FileReader(file));
		tweets = new ArrayList<Tweet>();
		String[] line;
		while ((line = reader.readNext()) != null) {			
			Tweet tweet = new Tweet();
			tweet.setLang(line[0]);
			tweet.setUser(line[1]);
			tweet.setDate(getDate(line[2]));
			tweet.setText(line[3]);
			tweets.add(tweet);
		}
		reader.close();
	}

	private String getDate(String string) throws ParseException {
		return (string.split(" "))[3];
	}

	public List<Tweet> getTweets() {
		return tweets;
	}
}
