package com.cc.StepDefinations;

import static org.junit.Assert.assertEquals;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.cc.model.CreditCard;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreditCardStepDefinationDelete {

	TestRestTemplate testRestTemplate=new TestRestTemplate();
	HttpHeaders httpHeader=new HttpHeaders();
	HttpEntity<CreditCard> httpEntity;
	ResponseEntity<String> response;
	String url;
	CreditCard creditCard;
	
	@Given("^Delete URI$")
	public void postUri() {
		 url="http://localhost:8080/credit-card/123456";
	}
	@When("^the client call delete uri$")
	public void clientPost() {
		response=testRestTemplate.exchange(url,HttpMethod.DELETE,httpEntity, String.class);
	}
	@Then("^client recieves status code 200$")
	public void returnResponse() {
		assertEquals(200, response.getStatusCodeValue());
	}
	
}
