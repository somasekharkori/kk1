package com.infy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class CardDetailsApplication {

	@Autowired
	private CardDetails cardDetails;
	public static void main(String[] args) {
		SpringApplication.run(CardDetailsApplication.class, args);
		System.out.println("card details service started");
	}
	
	/**
	 * Service to get card details
	 * @author Nitin Raut
	 * @param cardNo
	 * @return
	 */
	@GetMapping("/get-card-details/{cardNo}")
	public CardDetails getCardDetails(@PathVariable ("cardNo") String cardNo)
	{
		cardDetails.setCardNo(cardNo);
		cardDetails.setBank("ICICI");
		cardDetails.setCardType("VISA");
		cardDetails.setCvv("909");
		cardDetails.setExpiryDate("12/22");
		return cardDetails;
		
	}

}
