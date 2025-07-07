package utils;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {
	
	public Response hitGet(String baseUri, String path, String queryParams, String headers) {
		String[] headersArr = headers.split(";");
		Map<String, String> headersMap = new HashMap<>();
		if(!headers.equals("")) {
			for(String header: headersArr) {
				String headerKey = header.split(":")[0];
				String headerValue = header.split(":")[1];
				headersMap.put(headerKey, headerValue);
			}
		}
		
//		String[] queryParamArr = queryParams.split(";");
//		Map<String, String> queryParamsMap = new HashMap<>();
//		for(String queryParam : queryParamArr) {
//			String Key = queryParam.split("=")[0];
//			String Value = queryParam.split("=")[1];
//			queryParamsMap.put(Key, Value);
//		}
		String query = queryParams.equals("")?"":"?"+queryParams;
		path = path.equals("")?"":"/"+path;
		
		Response response = RestAssured.given()
				.headers(headersMap)
				.when().get(baseUri+path+query)
				.then().extract().response();
		return response;
				
	}
	
	public Response hitGet(String hostAndPort, String baseUri, String path, String queryParams, String headers) {
		String[] headersArr = headers.split(";");
		Map<String, String> headersMap = new HashMap<>();
		for(String header: headersArr) {
			String headerKey = header.split(":")[0];
			String headerValue = header.split(":")[1];
			headersMap.put(headerKey, headerValue);
		}
		
//		String[] queryParamArr = queryParams.split(";");
//		Map<String, String> queryParamsMap = new HashMap<>();
//		for(String queryParam : queryParamArr) {
//			String Key = queryParam.split("=")[0];
//			String Value = queryParam.split("=")[1];
//			queryParamsMap.put(Key, Value);
//		}
		String query = queryParams.equals("")?"":"?"+queryParams;
		path = path.equals("")?"":"/"+path;
		Response response = RestAssured.given().proxy(hostAndPort.split(":")[0], Integer.valueOf(hostAndPort.split(":")[1]))
				.headers(headersMap)
				.when().get(baseUri+path+query)
				.then().extract().response();
		return response;
				
	}
	
	public Response hitPost(String baseUri, String path, String queryParams, String headers,String body) {
		String[] headersArr = headers.split(";");
		Map<String, String> headersMap = new HashMap<>();
		if(!headers.equals("")) {
			for(String header: headersArr) {
				String headerKey = header.split(":")[0];
				String headerValue = header.split(":")[1];
				headersMap.put(headerKey, headerValue);
			}
		}
		
//		String[] queryParamArr = queryParams.split(";");
//		Map<String, String> queryParamsMap = new HashMap<>();
//		for(String queryParam : queryParamArr) {
//			String Key = queryParam.split("=")[0];
//			String Value = queryParam.split("=")[1];
//			queryParamsMap.put(Key, Value);
//		}
		String query = queryParams.equals("")?"":"?"+queryParams;
		path = path.equals("")?"":"/"+path;
		
		Response response = RestAssured.given()
				.headers(headersMap)
				.body(body)
				.when().post(baseUri+path+query)
				.then().extract().response();
		return response;
				
	}
	
	public Response hitPost(String hostAndPort,String baseUri, String path, String queryParams, String headers,String body) {
		String[] headersArr = headers.split(";");
		Map<String, String> headersMap = new HashMap<>();
		if(!headers.equals("")) {
			for(String header: headersArr) {
				String headerKey = header.split(":")[0];
				String headerValue = header.split(":")[1];
				headersMap.put(headerKey, headerValue);
			}
		}
		
//		String[] queryParamArr = queryParams.split(";");
//		Map<String, String> queryParamsMap = new HashMap<>();
//		for(String queryParam : queryParamArr) {
//			String Key = queryParam.split("=")[0];
//			String Value = queryParam.split("=")[1];
//			queryParamsMap.put(Key, Value);
//		}
		String query = queryParams.equals("")?"":"?"+queryParams;
		path = path.equals("")?"":"/"+path;
		
		Response response = RestAssured.given().proxy(hostAndPort.split(":")[0], Integer.valueOf(hostAndPort.split(":")[1]))
				.headers(headersMap)
				.body(body)
				.when().post(baseUri+path+query)
				.then().extract().response();
		return response;
				
	}
	
	public Response hitPut(String baseUri, String path, String queryParams, String headers,String body) {
		String[] headersArr = headers.split(";");
		Map<String, String> headersMap = new HashMap<>();
		if(!headers.equals("")) {
			for(String header: headersArr) {
				String headerKey = header.split(":")[0];
				String headerValue = header.split(":")[1];
				headersMap.put(headerKey, headerValue);
			}
		}
		
//		String[] queryParamArr = queryParams.split(";");
//		Map<String, String> queryParamsMap = new HashMap<>();
//		for(String queryParam : queryParamArr) {
//			String Key = queryParam.split("=")[0];
//			String Value = queryParam.split("=")[1];
//			queryParamsMap.put(Key, Value);
//		}
		String query = queryParams.equals("")?"":"?"+queryParams;
		path = path.equals("")?"":"/"+path;
		
		Response response = RestAssured.given()
				.headers(headersMap)
				.body(body)
				.when().put(baseUri+path+query)
				.then().extract().response();
		return response;
				
	}
	
	public Response hitPut(String hostAndPort,String baseUri, String path, String queryParams, String headers,String body) {
		String[] headersArr = headers.split(";");
		Map<String, String> headersMap = new HashMap<>();
		if(!headers.equals("")) {
			for(String header: headersArr) {
				String headerKey = header.split(":")[0];
				String headerValue = header.split(":")[1];
				headersMap.put(headerKey, headerValue);
			}
		}
		
//		String[] queryParamArr = queryParams.split(";");
//		Map<String, String> queryParamsMap = new HashMap<>();
//		for(String queryParam : queryParamArr) {
//			String Key = queryParam.split("=")[0];
//			String Value = queryParam.split("=")[1];
//			queryParamsMap.put(Key, Value);
//		}
		String query = queryParams.equals("")?"":"?"+queryParams;
		path = path.equals("")?"":"/"+path;
		
		Response response = RestAssured.given().proxy(hostAndPort.split(":")[0], Integer.valueOf(hostAndPort.split(":")[1]))
				.headers(headersMap)
				.body(body)
				.when().put(baseUri+path+query)
				.then().extract().response();
		return response;
				
	}

}
