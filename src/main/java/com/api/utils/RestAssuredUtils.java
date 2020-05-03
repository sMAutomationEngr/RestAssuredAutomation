package com.api.utils;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtils {

	// To create rest assured utlities as of now will create for POST and GET http
	// methods

	public Response callGet(String url, Map<String, String> headers, Map<String, String> queryParams) {
		RestAssured.baseURI = url;
		RequestSpecification httpRequest = RestAssured.given();
		if(headers!=null) {
			httpRequest.headers(headers);
			System.out.println(headers.toString());
		}
		if(queryParams!=null) {
			httpRequest.params(queryParams);
			System.out.println(queryParams.toString());
		}
		Response response = httpRequest.get();
		System.out.println(response.getBody().asString());
		return response;

	}

	public Response callPost(String url, Map<String, String>headers, Map<String, String> queryParams, Map<?, ?> requestBody) {
		RestAssured.baseURI=url;
		RequestSpecification httpRequest = RestAssured.given();
		if(headers!=null) {
			httpRequest.headers(headers);
			System.out.println(headers.toString());
		}
		if(queryParams!=null) {
			httpRequest.params(queryParams);
			System.out.println(queryParams.toString());
		}
		if(requestBody!=null) {
			httpRequest.body(requestBody);
		}
		Response response = httpRequest.post().andReturn();
		System.out.println(response.getBody().asString());
		return response;


	}

}
