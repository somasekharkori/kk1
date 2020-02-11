package com.cc.StepDefinations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.cc.model.CreditCard;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreditCardStepDefinationPost {

	TestRestTemplate testRestTemplate=new TestRestTemplate();
	HttpHeaders httpHeader=new HttpHeaders();
	HttpEntity<CreditCard> httpEntity;
	ResponseEntity<String> response;
	String url;
	CreditCard creditCard;
	
	@Given("^Post URI$")
	public void postUri() {
		 url="http://localhost:8080/credit-card";
	}
	@And("^set credit card details$")
	public void setCreditCardDetails() {
		DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate issueDate=LocalDate.parse("02-05-2019",dateFormatter);
		LocalDate expiryDate=LocalDate.parse("02-05-2022",dateFormatter);
		creditCard=new CreditCard();
		creditCard.setCardNumber(963L);
		creditCard.setCardHolderName("Naveen");
		creditCard.setCvv("023");
		creditCard.setIssueDate(issueDate);
		creditCard.setExpiryDate(expiryDate);
		httpEntity=new HttpEntity<CreditCard>(creditCard,httpHeader);
		
	}
	@When("^the client post credit card details$")
	public void clientPost() {
		response=testRestTemplate.exchange(url,HttpMethod.POST,httpEntity, String.class);

	}
	@Then("^recieves status code of 200$")
	public void returnResponse() {
		assertEquals(200, response.getStatusCodeValue());
	}
	
}
