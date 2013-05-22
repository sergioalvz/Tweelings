package com.salvarezsuar.feelinganalyzer.analyzer;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.tartarus.snowball.ext.SpanishStemmer;

import com.salvarezsuar.feelinganalyzer.dictionary.Dictionary;

@Path("/analyzer")
public class FeelingAnalyzer {

	private Dictionary dictionary;

	@GET
	@Path("/init")
	@Produces("text/plain")
	public String init() {
		dictionary = Dictionary.getInstance();
		try {
			dictionary.load();
		} catch (IOException e) {
			return "Dictionary not found";
		}
		return "Dictionary loaded";
	}

	@GET
	@Path("/tweet")
	@Produces("text/plain")
	public String analyze(@QueryParam("text") String text) {
		return _analyze(text);
	}

	@GET
	@Path("/stem")
	@Produces("application/json")
	public Set<String> stem(@QueryParam("word") String word) {
		String[] token = { word };
		return _stem(token);
	}

	@GET
	@Path("/tokenize")
	@Produces("application/json")
	public String[] tokenize(@QueryParam("text") String text) {
		return _tokenize(text);
	}

	private String _analyze(String text) {
		String[] tokens = _tokenize(text);
		Set<String> stems = _stem(tokens);
		Float value = obtainValue(stems);
		return value.toString();
	}

	private Set<String> _stem(String[] tokens) {
		SpanishStemmer stemmer = new SpanishStemmer();
		Set<String> stems = new TreeSet<String>();
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].equalsIgnoreCase("")) continue;
			stemmer.setCurrent(tokens[i]);
			stemmer.stem();
			stems.add(stemmer.getCurrent());
		}
		return stems;
	}

	private String[] _tokenize(String text) {
		String[] tokens = text.toLowerCase().split(" ");
		for (int i = 0; i < tokens.length; i++) {
			String current = tokens[i];
			if (current.equalsIgnoreCase("")) {
				continue;
			}
			if (current.length() == 1) {
				if (isPunctuation(current.toCharArray()[0])) tokens[i] = "";
			} else {
				char first = current.toCharArray()[0];
				char last = current.toCharArray()[current.length() - 1];
				String newWord = current;
				if (isPunctuation(last)) {
					newWord = "";
					for (int j = 0; j < current.length() - 1; j++) {
						newWord += current.toCharArray()[j];
					}
				}
				if (isPunctuation(first)) {
					if (newWord.length() == 1) {
						tokens[i] = "";
						continue;
					}
					String aux = newWord;
					newWord = "";
					for (int j = 1; j < aux.length(); j++) {
						newWord += aux.toCharArray()[j];
					}
				}
				tokens[i] = newWord;
			}
		}
		return tokens;
	}

	private boolean isPunctuation(char c) {
		switch (c) {
		case '¡':
		case '!':
		case '?':
		case '¿':
		case '.':
		case ',':
		case ';':
		case ':':
		case '(':
		case ')':
		case '-':
		case '"':
		case '\'':
		case '/':
		case '\\':
		case '@':
			return true;
		default:
			return false;
		}
	}

	private Float obtainValue(Set<String> stems) {
		Float value = 0.0f;
		int included = 0;
		for (String stem : stems) {
			if (dictionary.contains(stem)) {
				included++;
				value += dictionary.getValue(stem);
			}
		}
		return included == 0 ? 5 : value / included;
	}

}
