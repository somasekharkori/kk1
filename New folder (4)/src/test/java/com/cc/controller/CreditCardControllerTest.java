package com.cc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.cc.model.CreditCard;
import com.cc.services.CreditCardService;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardControllerTest {

	@InjectMocks
	private CreditCardController creditCardController;
	
	@Mock
	private CreditCardService creditCardService;
	
	CreditCard creditCard;
	
	@Before
	public void initialize() {
	creditCard=new CreditCard();
	creditCard.setCardHolderName("Nitin");
	creditCard.setCardNumber(123456L);
	creditCard.setCvv("002");
	creditCard.setExpiryDate(null);
	creditCard.setIssueDate(null);
  }

@Test
public void addCreditCardDetailsTest() {
	
	when(creditCardService.addCreditCardDetails(creditCard)).thenReturn(creditCard);
	ResponseEntity<?> actual=creditCardController.addCreditCardDetails(creditCard);
	verify(creditCardService,times(1)).addCreditCardDetails(creditCard);
	assertEquals(200,actual.getStatusCodeValue());
}

@Test
public void getCreditCardDetails() throws InterruptedException {
	when(creditCardService.getCreditCardDetails(123456L)).thenReturn(Optional.of(creditCard));
	ResponseEntity<?> actual=creditCardController.getCreditCardDetails(123456L);
	assertNotNull(actual);
	assertEquals(200,actual.getStatusCodeValue());
	verify(creditCardService,times(1)).getCreditCardDetails(123456L);
	
}

@Test
public void updateCreditCardDetailsTest() {
	when(creditCardService.updateCreditCardDetails(creditCard)).thenReturn(1);
	ResponseEntity<?> actual=creditCardController.updateCreditCardDetails(creditCard);
	assertEquals(1, actual.getBody());
	assertEquals(200, actual.getStatusCodeValue());
	verify(creditCardService,times(1)).updateCreditCardDetails(creditCard);
}

@Test
public void deleteCreditCardDetailsTest() {
	creditCardController.deleteCreditCardDetails(123456L);
    verify(creditCardService,times(1)).deleteCreditCardDetails(123456L);
}
}