package com.shri.rdbmstomongo;

public class Source {
	private String jdbcDriverClass;
	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public Source() {
		// TODO Auto-generated constructor stub
	}

	public String getJdbcDriverClass() {
		return jdbcDriverClass;
	}

	public void setJdbcDriverClass(String jdbcDriverClass) {
		this.jdbcDriverClass = jdbcDriverClass;
	}

	public String getJdbcConnectionUrl() {
		return jdbcConnectionUrl;
	}

	public void setJdbcConnectionUrl(String jdbcConnectionUrl) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
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
		return "Source [jdbcDriverClass=" + jdbcDriverClass + ", jdbcConnectionUrl=" + jdbcConnectionUrl + ", username="
				+ username + ", password=" + password + "]";
	}

}
