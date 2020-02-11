package com.cc.StepDefinations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.cc.model.CreditCard;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreditCardStepDefination {

	TestRestTemplate testRestTemplate=new TestRestTemplate();
	HttpHeaders httpHeader=new HttpHeaders();
	HttpEntity<CreditCard> httpEntity=new HttpEntity<CreditCard>(null,httpHeader);
	ResponseEntity<String> response;
	String url;
	
	@Given("^Get URI$")
	public void getUri() {
		 url="http://localhost:8080/credit-card";
	}
	@When("^the client calls$")
	public void clientCall() {
		response=testRestTemplate.exchange(url+"/123456",HttpMethod.GET,httpEntity, String.class);

	}
	@Then("^the client receives status code of 200$")
	public void response() {
		assertEquals(200, response.getStatusCodeValue());
	}
	@And("^the client receives credit card details$")
	public void and() {
		
		String expected="{\"cardNumber\":123456,\"cardHolderName\":\"Nitin\",\"cvv\":\"002\",\"expiryDate\":\"10-11-2022\",\"issueDate\":\"10-11-2019\"}";
		assertEquals(expected, response.getBody());
		assertNotNull(response.getBody());
	}
}
