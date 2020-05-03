package com.api.utils;
/**
 * 
 * @author sudip.majumdar
 * 
 */
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtils {

	// rest assured utils as of now created only for POST and GET http
	// methods

	public Response callGet(String url, Map<String, String> headers, Map<String, String> queryParams,
			Map<?, ?> requestBody) {
		RequestSpecification request = getRequest(url, headers, queryParams, requestBody);
		Response response = request.get();
		System.out.println(response.getBody().asString());
		return response;

	}

	public Response callPost(String url, Map<String, String> headers, Map<String, String> queryParams,
			Map<?, ?> requestBody) {
		RequestSpecification request = getRequest(url, headers, queryParams, requestBody);
		Response response = request.post().andReturn();
		System.out.println(response.getBody().asString());
		return response;

	}
	
	public Response callPost(String url, Map<String, String> headers, Map<String, String> queryParams,
			Object requestBody) {
		RequestSpecification request = getRequest(url, headers, queryParams, requestBody);
		Response response = request.post().andReturn();
		System.out.println(response.getBody().asString());
		return response;

	}
	
	public RequestSpecification getRequest(String url, Map<String, String> headers, Map<String, String> queryParams,
			Object requestBody) {

		RestAssured.baseURI = url;
		RequestSpecification httpRequest = RestAssured.given();
		if (headers != null) {
			httpRequest.headers(headers);
			System.out.println(headers.toString());
		}
		if (queryParams != null) {
			httpRequest.params(queryParams);
			System.out.println(queryParams.toString());
		}
		if (requestBody != null) {
			httpRequest.body(requestBody);
			System.out.println(requestBody.toString());
		}
		return httpRequest;

	}


	public RequestSpecification getRequest(String url, Map<String, String> headers, Map<String, String> queryParams,
			Map<?, ?> requestBody) {

		RestAssured.baseURI = url;
		RequestSpecification httpRequest = RestAssured.given();
		if (headers != null) {
			httpRequest.headers(headers);
			System.out.println(headers.toString());
		}
		if (queryParams != null) {
			httpRequest.params(queryParams);
			System.out.println(queryParams.toString());
		}
		if (requestBody != null) {
			httpRequest.body(requestBody);
			System.out.println(requestBody.toString());
		}
		return httpRequest;

	}

}
