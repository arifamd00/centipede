package com.centipede.centipede;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import utils.ExcelUtils;
import utils.RestUtils;
import utils.csvReader;

public class TestRunner {
	
	WebDriver driver;
	RestUtils restUtils = new RestUtils();
	ExcelUtils excel = new ExcelUtils();
	private Logger log = LogManager.getLogger(TestRunner.class);
	@BeforeMethod
	public void settingUp() {
		
	}
	
	@Test(dataProvider="csvReader2", dataProviderClass = csvReader.class, invocationCount = 1, threadPoolSize = 2)
	public void crawl(Object[] data) {
		System.out.println("Thread: "+Thread.currentThread().getId());
		String testCaseId = data[0].toString();
		String testScenario = data[1].toString();
		String method = data[3].toString();
		String proxy = data[2].toString();
		String baseUri = data[4].toString();
		String headers = data[5].toString();
		String paths = data[6].toString();
		String queryParams = data[7].toString();
		String body = data[8].toString();
		
		int expectedStatusCode = Integer.valueOf(data[9].toString());
		String responseSchema = data[10].toString();
		int actualStatusCode = -1;
		Response response = null;
		String comment = "";
		
		if(method.equalsIgnoreCase("get")) {
			if(!proxy.equalsIgnoreCase("")) {
				response = restUtils.hitGet(proxy, baseUri, paths, queryParams, headers);
				
			}else {
				response = restUtils.hitGet(baseUri, paths, queryParams, headers);
			}
			actualStatusCode = response.statusCode();
			
			System.out.println(response.getBody().asString());
			
		}else if(method.equalsIgnoreCase("post")) {
			if(!proxy.equalsIgnoreCase("")) {
				response = restUtils.hitPost(proxy, baseUri, paths, queryParams, headers,body);
				
			}else {
				response = restUtils.hitPost(baseUri, paths, queryParams, headers,body);
			}
			actualStatusCode = response.statusCode();
			
			System.out.println(response.getBody().asString());
			
		}else if(method.equalsIgnoreCase("put")) {
			if(!proxy.equalsIgnoreCase("")) {
				response = restUtils.hitPost(proxy, baseUri, paths, queryParams, headers,body);
				
			}else {
				response = restUtils.hitPost(baseUri, paths, queryParams, headers,body);
			}
			actualStatusCode = response.statusCode();
			
			System.out.println(response.getBody().asString());
		}
		
		if(!responseSchema.equals("")) {
			try {
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(responseSchema));
			}catch(Exception e) {
				comment +=e.getLocalizedMessage().toString();
			}
		}
		
		if(actualStatusCode!=expectedStatusCode) {
			comment+="\nexpected status code: "+expectedStatusCode+", but got "+actualStatusCode;
		}
		
		System.out.println(comment);
		//Assert.assertTrue(comment.equals(""), comment.toString());
		
		// decision
		String decision="";
		if(actualStatusCode!=expectedStatusCode) {
			decision="Fail";
		}else {
			decision="Pass";
		}
		
		Object[][] results = {{testCaseId, testScenario, baseUri+paths+queryParams, method, response.getBody().asPrettyString(), response.getHeaders().toString(),response.getTime(),expectedStatusCode, actualStatusCode, decision, comment}};
		excel.publishReport(results);
		
	}
	@AfterMethod
	public void tearDown() {
		
		
		
	}
	

}
