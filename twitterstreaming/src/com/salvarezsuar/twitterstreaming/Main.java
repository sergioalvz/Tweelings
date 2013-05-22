package com.salvarezsuar.twitterstreaming;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println();
		System.out.println();
		if (!(args.length > 0)) {
			System.err.println("No se han pasado los argumentos necesarios.");
			return;
		}

		final String path = args[0];
		System.out.println("Se creara un fichero en la siguiente ruta: " + path);
		System.out.println();
		System.out.println("Se quiere buscar sobre los siguientes terminos: ");
		String[] tracks = new String[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			System.out.println("\t" + args[i]);
			tracks[i - 1] = args[i];
		}
		System.out.println();

		TwitterStreaming twitter = new TwitterStreaming(path, tracks);
		try {
			twitter.open();
			twitter.run();

			int option = -1;
			Scanner scan = new Scanner(System.in);
			do {
				System.out.println("----------------------");
				System.out.println("  0. Parar y Salir");
				System.out.println("----------------------");
				option = scan.nextInt();
			} while (option != 0);
			twitter.close();
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Bye bye!");
	}
}
