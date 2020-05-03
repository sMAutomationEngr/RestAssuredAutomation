package com.api.utils;

/**
 * 
 * 
 * @author sudip.majumdar
 *
 */

public class GenerateTokenInputDTO {

	private String userName;
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//parameterized constructor
	public GenerateTokenInputDTO(String userName, String password) {
		this.userName=userName;
		this.password=password;
	}
}
