package com.salvarezsuar.twitterfeelinganalyzer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertiesWriter {
	public static void write(Map<String, Float> values, String path) throws IOException {
		Properties props = new Properties();
		for (String date : values.keySet()) {
			props.setProperty(date, values.get(date).toString());
		}
		props.store(new FileOutputStream(path + "-results"), "");
	}
}
