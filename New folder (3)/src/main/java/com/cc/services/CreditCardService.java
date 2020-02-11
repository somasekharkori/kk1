package com.cc.services;

import java.util.Optional;

import com.cc.model.CreditCard;

public interface CreditCardService {
	public Optional<CreditCard> getCreditCardDetails(Long cardNumber);
	public CreditCard addCreditCardDetails(CreditCard creditCard);
	public int updateCreditCardDetails(CreditCard creditCard);
	public void deleteCreditCardDetails(Long cardNumber);

}
