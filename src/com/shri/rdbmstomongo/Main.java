package com.shri.rdbmstomongo;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static void main(String[] args) {
		validateArguments(args);
		Config config = getConfig(args);
		readData(config);
	}

	private static void readData(Config config) {
		String jdbcDriverClass = config.getSource().getJdbcDriverClass();
		String jdbcConnectionUrl = config.getSource().getJdbcConnectionUrl();
		String username = config.getSource().getUsername();
		String password = config.getSource().getPassword();
		try {
			Class.forName(jdbcDriverClass);
		} catch (ClassNotFoundException e) {
			System.out.println("The JDBC Driver Class '" + jdbcDriverClass + "' is not available in the classpath.");
			showHelp();
		}
		try {
			Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
			Statement statement = connection.createStatement();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			ResultSet tables = databaseMetaData.getTables(null, null, null, new String[] { "TABLE" });
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				String query = "SELECT * FROM " + tableName;
				ResultSet queryResult = statement.executeQuery(query);
				ResultSetMetaData tableMetadata = queryResult.getMetaData();
				int columnCount = tableMetadata.getColumnCount();
				while(queryResult.next()) {
					
				}
			}
		} catch (SQLException e) {
			System.out.println("Incorrect Connection URL or DB credentials");
			System.out.println(e.getMessage());
			showHelp();
		}
	}

	private static Config getConfig(String[] args) {
		File configFile = new File(args[0]);
		try {
			Config config = OBJECT_MAPPER.readValue(configFile, Config.class);
			validateConfig(config);
			return config;
		} catch (IOException e) {
			System.out.println("Non parsable JSON at '" + configFile.getAbsolutePath() + "'");
			System.out.println(e.getMessage());
			showHelp();
		}
		return null;
	}

	private static void validateArguments(String[] args) {
		if (args.length != 1) {
			System.out.println("Received " + args.length + " arguments. Expected 1");
			showHelp();
		}
		File configFile = new File(args[0]);
		if (!configFile.exists()) {
			System.out.println("The file '" + configFile.getAbsolutePath() + "' doesn't exist.");
			showHelp();
		}
		if (!configFile.isFile()) {
			System.out
					.println("The path '" + configFile.getAbsolutePath() + "' point to a directory instead of a file.");
			showHelp();
		}
	}

	private static void validateConfig(Config config) {
		Source source = config.getSource();
		Destination destination = config.getDestination();
		if (source.getJdbcConnectionUrl() == null || source.getJdbcConnectionUrl().isEmpty()) {
			System.out.println("JDBC Connection URL is missing");
			showHelp();
		}
		if (source.getJdbcDriverClass() == null || source.getJdbcDriverClass().isEmpty()) {
			System.out.println("JDBC Driver ClassName is missing");
			showHelp();
		}
		if (!config.getToJson()) {
			if (destination.getMongoHost() == null || destination.getMongoHost().isEmpty()) {
				System.out.println("MongoDB host is missing");
				showHelp();
			}
			if (destination.getMongoPort() == null || destination.getMongoPort().isEmpty()) {
				System.out.println("MongoDB port is missing");
				showHelp();
			}
			if (destination.getDatabase() == null || destination.getDatabase().isEmpty()) {
				System.out.println("MongoDB database name is missing");
				showHelp();
			}
		} else {
			if (config.getJsonOutputDirectory() == null || config.getJsonOutputDirectory().isEmpty()) {
				System.out.println("JSON output directory path is missing");
				showHelp();
			}
		}
	}

	private static void showHelp() {
		System.out.println("RDBMS to MONGO");
		System.out.println("USAGE: ");
		System.out.println("\trdbmstomongo <path-to-config-json>");
		System.exit(0);
	}
}
