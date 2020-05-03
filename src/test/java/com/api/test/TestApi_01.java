package com.api.test;

import java.io.IOException;

/**
 * 
 * @author sudip.majumdar
 * 
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.utils.GenerateTokenDTO;
import com.api.utils.GenerateTokenInputDTO;
import com.api.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestApi_01 {

	private RestAssuredUtils restUtils = new RestAssuredUtils();
	public String host = "http://ergast.com";
	public String path = "/api/f1/2008/1.json";
	SoftAssert softAssert = new SoftAssert();
	// Calling GET method and validating the response

	@Test(enabled = true)
	public void getApiResponseWithoutUsingUtils() {
		RestAssured.baseURI = host + path;
		RequestSpecification httprequest = RestAssured.given();
		// header
		httprequest.header("Content-Type", "application/json");
		// response
		Response response = httprequest.get();
		System.out.println("API response is " + response.getBody().asString());

		// To validate json response using JSONObject
		JSONObject json = new JSONObject(response.getBody().asString());
		String series = json.getJSONObject("MRData").getString("series");
		String xmlns = json.getJSONObject("MRData").getString("xmlns");
		String url = json.getJSONObject("MRData").getString("url");
		String limit = json.getJSONObject("MRData").getString("limit");
		String offset = json.getJSONObject("MRData").getString("offset");
		String total = json.getJSONObject("MRData").getString("total");
		String season = json.getJSONObject("MRData").getJSONObject("RaceTable").getString("season");
		String round = json.getJSONObject("MRData").getJSONObject("RaceTable").getString("round");
		JSONObject races = (JSONObject) json.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races")
				.get(0);
		races.get("season");
		races.get("round");
		races.get("url");
		races.get("raceName");
		races.get("date");
		races.get("time");

		// or to access json array
		if (races != null) {
			JSONArray raceArray = json.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
			Iterator<Object> iterator = raceArray.iterator();
			while (iterator.hasNext()) {
				System.out.println();
				System.out.println(iterator.next());
			}

		}

		System.out.println(series);
		System.out.println(xmlns);
		System.out.println(url);
		System.out.println(limit);
		System.out.println(offset);
		System.out.println(total);
		System.out.println(season);
		System.out.println(round);
		System.out.println(races.get("season"));
		System.out.println(races.get("round"));
		System.out.println(races.get("url"));
		System.out.println(races.get("raceName"));
		System.out.println(races.get("date"));
		System.out.println(races.get("time"));

		softAssert.assertEquals(series, "f1");
		softAssert.assertEquals(xmlns, "http://ergast.com/mrd/1.4");
		softAssert.assertEquals(url, "http://ergast.com/api/f1/2008/1.json");
		softAssert.assertEquals(limit, "30");
		softAssert.assertEquals(offset, "0");
		softAssert.assertEquals(total, "1");
		softAssert.assertEquals(season, "2008");
		softAssert.assertEquals(round, "1");
		softAssert.assertEquals(races.get("season"), season);
		softAssert.assertEquals(races.get("round"), round);
		softAssert.assertEquals(races.get("url"), "http://en.wikipedia.org/wiki/2008_Australian_Grand_Prix");
		softAssert.assertEquals(races.get("raceName"), "Australian Grand Prix");
		softAssert.assertEquals(races.get("date"), "2008-03-16");
		softAssert.assertEquals(races.get("time"), "04:30:00Z");

	}

	@Test(enabled = true)
	public void getCallUsingUtils() {
		String baseUri = host + path;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		Response response = restUtils.callGet(baseUri, headers, null, null);
		System.out.println(response.getBody().asString());
		// To validate response

	}

	@Test(enabled = true)
	public void postCallUsingUtils() throws JsonParseException, JsonMappingException, IOException {
		String url = "http://bookstore.toolsqa.com/Account/v1/GenerateToken";
		String uname = "anushahhhhhh";
		String pass = "Anu@1222123";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Connection", "keep-alive");
		Map<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("userName", uname);
		requestBody.put("password", pass);
		Response response = restUtils.callPost(url, headers, null, requestBody);
		System.out.println(response.getBody().asString());
		
		//Deserialize the json response into a DTO/POJO class
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GenerateTokenDTO mappedResponse = mapper.readValue(response.getBody().asString(), GenerateTokenDTO.class);
		System.out.println(mappedResponse.getToken());
		System.out.println(mappedResponse.getExpires());
		System.out.println(mappedResponse.getResult());
		System.out.println(mappedResponse.getStatus());
		
		//Validating the response
		softAssert.assertNotNull(mappedResponse.getToken(), "Token value is coming as null");
		softAssert.assertNotNull(mappedResponse.getExpires(), "Expires key is coming as null");
		softAssert.assertEquals(mappedResponse.getResult(), "User authorized successfully.");
		softAssert.assertEquals(mappedResponse.getStatus(), "Success");
		

	}
	
	@Test(dataProvider = "dpInputs", enabled = true)
	public void postCallUsingSetOfData(GenerateTokenInputDTO request) {
		
		String url = "http://bookstore.toolsqa.com/Account/v1/GenerateToken";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Connection", "keep-alive");
		Response response = restUtils.callPost(url, headers, null, request);
		System.out.println(response.getBody().asString());
		
		
	}
	
	@DataProvider(name = "dpInputs")
	public Object[][] dpForInputs(){
		return new Object[][] {
			{new GenerateTokenInputDTO("anushahhhhhh", "Anu@1222123")}
		};
	}

}
