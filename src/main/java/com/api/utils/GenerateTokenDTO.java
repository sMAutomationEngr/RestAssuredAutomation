package com.api.utils;

/**
 * 
 * 
 * @author sudip.majumdar
 *
 */


public class GenerateTokenDTO {
	
	private String token;
	private String expires;
	private String status;
	private String result;
	
	public void setToken(String token) {
		this.token=token;
	}
	public String getToken() {
		return token;
	}
	public String getExpires() {
		return expires;
	}
	public void setExpires(String expires) {
		this.expires = expires;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}
