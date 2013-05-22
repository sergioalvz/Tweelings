package com.salvarezsuar.tweelings.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC {
	public static Connection getConnection() {

		Properties properties = new Properties();
		try {
			properties.load(JDBC.class.getResourceAsStream("/dbconfiguration.properties"));
		} catch (IOException e) {
			throw new RuntimeException("Fichero properties no encontrado", e);
		}
		loadDriver(properties);
		String SQL_URL = properties.getProperty("SQL_URL");
		String SQL_USER = properties.getProperty("SQL_USER");
		String SQL_PASS = properties.getProperty("SQL_PASS");
		try {
			return DriverManager.getConnection(SQL_URL, SQL_USER, SQL_PASS);
		} catch (SQLException e) {
			throw new RuntimeException("No se puede abrir conexion a " + SQL_URL, e);
		}
	}

	private static void loadDriver(Properties properties) {
		try {
			Class.forName(properties.getProperty("SQL_DRIVER"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver SQL no encontrado", e);
		}
	}
}
