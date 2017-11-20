package com.shri.rdbmstomongo;

public class Destination {
	private String mongoHost;
	private String mongoPort;
	private String database;
	private String username;
	private String password;

	public Destination() {
		// TODO Auto-generated constructor stub
	}

	public String getMongoHost() {
		return mongoHost;
	}

	public void setMongoHost(String mongoHost) {
		this.mongoHost = mongoHost;
	}

	public String getMongoPort() {
		return mongoPort;
	}

	public void setMongoPort(String mongoPort) {
		this.mongoPort = mongoPort;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Destination [mongoHost=" + mongoHost + ", mongoPort=" + mongoPort + ", database=" + database
				+ ", username=" + username + ", password=" + password + "]";
	}

}
