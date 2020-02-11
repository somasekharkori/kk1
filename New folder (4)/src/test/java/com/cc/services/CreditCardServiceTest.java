package com.cc.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cc.model.CreditCard;
import com.cc.repositories.CreditCardRepository;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceTest {

	@InjectMocks
	private CreditCardServiceImpl creditCardService;
	
	@Mock
	private CreditCardRepository creditCardRepository;
	
	private CreditCard creditCard;
	
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
		
		when(creditCardRepository.saveAndFlush(creditCard)).thenReturn(creditCard);
		CreditCard actual=creditCardService.addCreditCardDetails(creditCard);
		verify(creditCardRepository,times(1)).saveAndFlush(creditCard);
		assertEquals("Nitin", actual.getCardHolderName());
		assertNotNull(actual);
	}
	
	@Test
	public void getCreditCardDetails() {
		when(creditCardRepository.findById(123456L)).thenReturn(Optional.of(creditCard));
		Optional<CreditCard> actual=creditCardService.getCreditCardDetails(123456L);
		assertNotNull(actual);
		assertEquals(123456, actual.get().getCardNumber().longValue());
		//assertSame(creditCard, actual);
		assertTrue(actual.isPresent());
		assertNotEquals("Naveen", actual.get().getCardHolderName());
		verify(creditCardRepository,times(1)).findById(123456L);
		
	}
	
	@Test
	public void updateCreditCardDetailsTest() {
		when(creditCardRepository.updateCreditCardDetails("Nitin", "002", 123456L)).thenReturn(1);
		int actual=creditCardService.updateCreditCardDetails(creditCard);
		assertEquals(1, actual);
		verify(creditCardRepository,times(1)).updateCreditCardDetails("Nitin", "002", 123456L);
	}
	
	@Test
	public void deleteCreditCardDetailsTest() {

		creditCardService.deleteCreditCardDetails(123456L);
     verify(creditCardRepository,times(1)).deleteById(123456L);
	}
}
