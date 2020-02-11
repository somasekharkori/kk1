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

public class CreditCardStepDefinationPut {

	TestRestTemplate testRestTemplate=new TestRestTemplate();
	HttpHeaders httpHeader=new HttpHeaders();
	HttpEntity<CreditCard> httpEntity;
	ResponseEntity<String> response;
	String url;
	CreditCard creditCard;
	
	@Given("^Put URI$")
	public void postUri() {
		 url="http://localhost:8080/credit-card";
	}
	@And("^Set credit card details to modify$")
	public void setCreditCardDetails() {
		DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate issueDate=LocalDate.parse("02-05-2019",dateFormatter);
		LocalDate expiryDate=LocalDate.parse("02-05-2022",dateFormatter);
		creditCard=new CreditCard();
		creditCard.setCardNumber(963L);
		creditCard.setCardHolderName("Naresh");
		creditCard.setCvv("000");
		creditCard.setIssueDate(issueDate);
		creditCard.setExpiryDate(expiryDate);
		httpEntity=new HttpEntity<CreditCard>(creditCard,httpHeader);
		
	}
	@When("^the client send put request$")
	public void clientPost() {
		response=testRestTemplate.exchange(url,HttpMethod.PUT,httpEntity, String.class);

	}
	@Then("^the client receives 1$")
	public void returnResponse() {
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(1,Integer.parseInt(response.getBody()));
	}
	
}
