package com.salvarezsuar.twitterfeelinganalyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxrs.client.WebClient;

import com.salvarezsuar.twitterfeelinganalyzer.model.Tweet;

public class AnalyzerManager {

	private Map<String, Float> values;

	public AnalyzerManager() {
		values = new HashMap<String, Float>();
	}

	public void analyze(List<Tweet> tweets) {
		if (!initializeWebService()) throw new IllegalStateException("No se ha podido iniciar el WS analizador");
		for (Tweet tweet : tweets) {
			String date = removeSeconds(tweet.getDate());
			Float value = getValue(tweet.getText());
			if (value > 5.3f || value < 4.7f) {
				if (values.containsKey(date)) {
					Float newValue = (values.get(date) + value) / 2;
					values.put(date, newValue);
				} else {
					values.put(date, value);
				}
			}
		}

	}

	private boolean initializeWebService() {
		WebClient client = WebClient.create("http://localhost:8080");
		String init = client.path("/feelinganalyzer/analyzer/init").accept("text/plain").get(String.class);

		return init.compareToIgnoreCase("Dictionary loaded") == 0;
	}

	private String removeSeconds(String date) {
		String[] pieces = date.split(":");
		return pieces[0] + "." + pieces[1];
	}

	private Float getValue(String text) {
		Float result = 5f;
		WebClient client = WebClient.create("http://localhost:8080");
		result = Float.valueOf(client.path("/feelinganalyzer/analyzer/tweet").query("text", text).accept("text/plain")
				.get(String.class));
		return result;
	}

	public Map<String, Float> getValuesByDate() {
		return values;
	}

}
