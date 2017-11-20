package com.shri.rdbmstomongo;

public class Config {
	private Source source;
	private Destination destination;
	private Boolean toJson;
	private String jsonOutputDirectory;

	public Config() {
		// TODO Auto-generated constructor stub
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Boolean getToJson() {
		return toJson;
	}

	public void setToJson(Boolean toJson) {
		this.toJson = toJson;
	}

	public String getJsonOutputDirectory() {
		return jsonOutputDirectory;
	}

	public void setJsonOutputDirectory(String jsonOutputDirectory) {
		this.jsonOutputDirectory = jsonOutputDirectory;
	}

	@Override
	public String toString() {
		return "Config [source=" + source + ", destination=" + destination + ", toJson=" + toJson
				+ ", jsonOutputDirectory=" + jsonOutputDirectory + "]";
	}

}
