package com.cc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.exceptions.CreditCardNotFound;
import com.cc.model.CreditCard;
import com.cc.repositories.CreditCardRepository;
/**
 * service class to implementation of credit card
 * @author Nitin Raut
 *
 */
@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Override
	public Optional<CreditCard> getCreditCardDetails(Long cardNumber) {

		Optional<CreditCard> creditCard = creditCardRepository.findById(cardNumber);
		if (!creditCard.isPresent()) {
			throw new CreditCardNotFound();
		} else {
			return creditCard;
		}
	}

	@Override
	public CreditCard addCreditCardDetails(CreditCard creditCard) {

		return creditCardRepository.saveAndFlush(creditCard);
	}

	@Override
	public int updateCreditCardDetails(CreditCard creditCard) {

		return creditCardRepository.updateCreditCardDetails(creditCard.getCardHolderName(), creditCard.getCvv(),
				creditCard.getCardNumber());
	}

	@Override
	public void deleteCreditCardDetails(Long cardNumber) {
		try {
			creditCardRepository.deleteById(cardNumber);
		} catch (EmptyResultDataAccessException ex) {
			throw new CreditCardNotFound();
		}
	}

}
