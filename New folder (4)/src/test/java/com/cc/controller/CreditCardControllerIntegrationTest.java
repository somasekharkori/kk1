package com.cc.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cc.CreditcardApplication;
import com.cc.model.CreditCard;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CreditcardApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreditCardControllerIntegrationTest {

	@LocalServerPort
	private int port;
	
	TestRestTemplate testRestTemplate;
	HttpEntity<CreditCard> httpEntity;
	CreditCard creditCard;
	String baseUrl;
	
	@Before
	public void initialize() {
	creditCard=new CreditCard();
	creditCard.setCardHolderName("Nitin");
	creditCard.setCardNumber(123456L);
	creditCard.setCvv("002");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate issueDate=LocalDate.parse("10-11-2019",formatter);
    LocalDate expireDate = LocalDate.parse("10-11-2022",formatter); 
	creditCard.setExpiryDate(expireDate);
	creditCard.setIssueDate(issueDate);
	testRestTemplate=new TestRestTemplate();
    baseUrl="http://localhost:"+port+"/credit-card";
	httpEntity=new HttpEntity<CreditCard>(creditCard,null);		
	
  }
	@Test
	public void getCreditCardDetailsTest() throws JSONException {
		ResponseEntity<String> response=testRestTemplate.exchange(baseUrl+"/123456",HttpMethod.GET,null,String.class);
		String expected="{\"cardNumber\":123456,\"cardHolderName\":\"Nitin\",\"cvv\":\"002\",\"expiryDate\":\"10-11-2022\",\"issueDate\":\"10-11-2019\"}";
		JSONAssert.assertEquals(expected, response.getBody(),false);
		assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void addCreditCardDetails() {
		ResponseEntity<String> response=testRestTemplate.exchange(baseUrl,HttpMethod.POST,httpEntity,String.class);
		assertEquals(200, response.getStatusCodeValue());

	}
	
	@Test
	public void updateCreditCardDetailsTest() {
		ResponseEntity<Integer> response=testRestTemplate.exchange(baseUrl,HttpMethod.PUT,httpEntity,Integer.class);
		assertEquals(1,response.getBody().intValue());
		assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void deleteCreditCardDetailsTest() {
		ResponseEntity<String> response=testRestTemplate.exchange(baseUrl+"/123456", HttpMethod.DELETE,null,String.class);
		String expected="CreditCard Details Deleted Successfully";
		String actual=response.getBody();
		assertEquals(expected, actual);
		assertEquals(200, response.getStatusCodeValue());
	}
}
