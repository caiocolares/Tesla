package br.com.tesla.conf.data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="tesla.datasource")
public class DataSourceProperties {

	private String user;
	private String password;
	private String server;
	private String database;
	
	@Override
	public String toString() {
		return user+"/"+password+"/"+server+"/"+database;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	
}
