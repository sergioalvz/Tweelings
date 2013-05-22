package com.salvarezsuar.twitterfeelinganalyzer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Debes pasar la ruta del archivo con los tweets a analizar.");
			return;
		}
		String file = args[0];
		System.out.println("Se va a analizar el fichero: " + file);
		TweetLoader loader = new TweetLoader();
		try {
			loader.load(file);
			System.out.println("Se ha cargado el fichero correctamente");
			AnalyzerManager analyzer = new AnalyzerManager();
			System.out.println("Analizando tweets...");
			analyzer.analyze(loader.getTweets());
			Map<String, Float> values = analyzer.getValuesByDate();
			System.out.println("Escribiendo resultados... " + file + "-results");
			PropertiesWriter.write(values, file);
			System.out.println("Proceso de analisis completado");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
}
