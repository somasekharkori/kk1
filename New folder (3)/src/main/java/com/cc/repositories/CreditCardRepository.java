package com.cc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cc.model.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{

	@Modifying
	@Query("update CreditCard cc set cc.cardHolderName=?1,cc.cvv=?2 where cc.cardNumber=?3")
	public int updateCreditCardDetails(String cardHolderName,String cvv,Long cardNumber);
}
