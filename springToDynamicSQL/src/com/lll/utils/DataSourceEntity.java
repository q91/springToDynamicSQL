package com.lll.utils;

public class DataSourceEntity {
	private String ip;
	private Integer port;
	private String service;
	private String username;
	
	public DataSourceEntity(String ip, Integer port2, String service, String username) {
		super();
		this.ip = ip;
		this.port = port2;
		this.service = service;
		this.username = username;
	}
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
