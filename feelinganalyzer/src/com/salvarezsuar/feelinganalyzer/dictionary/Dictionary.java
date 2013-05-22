package com.salvarezsuar.feelinganalyzer.dictionary;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.tartarus.snowball.ext.SpanishStemmer;

import au.com.bytecode.opencsv.CSVReader;

public class Dictionary {
	public Map<String, Float> dictionary = null;
	private static Dictionary instance;

	public static Dictionary getInstance() {
		if (instance == null) instance = new Dictionary();
		return instance;
	}

	public void load() throws IOException {
		if (dictionary != null) return;
		dictionary = new HashMap<String, Float>();
		Map<String, Float> words = loadFromCSVFile();
		for (String word : words.keySet()) {
			Float value = words.get(word);
			dictionary.put(getStem(word), value);
		}
	}

	private Map<String, Float> loadFromCSVFile() throws FileNotFoundException, IOException {
		Map<String, Float> words = new HashMap<String, Float>();
		CSVReader csvReader = new CSVReader(new FileReader("/words.csv"));
		String[] line = csvReader.readNext();
		while ((line = csvReader.readNext()) != null) {
			String word = line[2];
			Float value = Float.valueOf(line[3]);
			if (words.containsKey(word)) {
				value = (words.get(word) + value) / 2;
			}
			words.put(word, value);
		}
		csvReader.close();

		return words;
	}

	public Float getValue(String word) {
		return dictionary.get(word);
	}

	private String getStem(String token) {
		SpanishStemmer stemmer = new SpanishStemmer();
		stemmer.setCurrent(token);
		stemmer.stem();
		return stemmer.getCurrent();
	}

	public boolean contains(String token) {
		return dictionary.containsKey(token);
	}

}
