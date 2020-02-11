package com.cc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cc.model.CreditCard;
import com.cc.services.CreditCardService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Rest Controller for Credit Card Endpoints
 * @author Nitin Raut
 *
 */
@RestController
@Api(value="Credit Card Service",description = "Operation pertaining to Credit Card details")
public class CreditCardController {

	@Autowired
	private CreditCardService creditCardService;
	
	@ApiOperation(value="Get credit card deatils by credit card number")
	@ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved credit card details"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
	            @ApiResponse(code=500, message="credit card not found against given credit card number")})
	@PreAuthorize("#oauth2.hasScope('profile')")
	@RequestMapping(value="/credit-card/{cardNumber}",method=RequestMethod.GET,produces="application/json")
	@HystrixCommand(fallbackMethod="getCreditCardDetailsFallback",commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="10000")},ignoreExceptions = {Exception.class})
	public ResponseEntity<?> getCreditCardDetails(@PathVariable("cardNumber") Long cardNumber) throws InterruptedException {
		return new ResponseEntity<>(creditCardService.getCreditCardDetails(cardNumber),HttpStatus.OK);
	}
	
	@ApiOperation(value="Save the credit card details")
	@RequestMapping(value="/credit-card",method=RequestMethod.POST,produces="application/json",consumes="application/json")
	public ResponseEntity<?> addCreditCardDetails(@RequestBody @Valid CreditCard creditCard){
		
		creditCardService.addCreditCardDetails(creditCard);
		return new ResponseEntity<>(creditCard,HttpStatus.OK);
	}
	
	@ApiOperation(value="Update credit card details")
	@RequestMapping(value="/credit-card",method=RequestMethod.PUT,produces="application/json",consumes="application/json")
	public ResponseEntity<?> updateCreditCardDetails(@RequestBody CreditCard creditCard){
		return new ResponseEntity<>(creditCardService.updateCreditCardDetails(creditCard),HttpStatus.OK);
	}
	
	@ApiOperation(value="Delete credit card details by credit card number")
	@RequestMapping(value="/credit-card/{cardNumber}",method=RequestMethod.DELETE,produces="application/json")
	public ResponseEntity<?> deleteCreditCardDetails(@PathVariable("cardNumber") Long cardNumber){
		creditCardService.deleteCreditCardDetails(cardNumber);
		return new ResponseEntity<>("CreditCard Details Deleted Successfully",HttpStatus.OK);
	}
	
	/**
	 * This is fallback method
	 * @param cardNumber
	 * @return
	 */
	public ResponseEntity<?> getCreditCardDetailsFallback(Long cardNumber){
		return new ResponseEntity<>("Service is down...please visit after sometime",HttpStatus.SERVICE_UNAVAILABLE);
	}
	
}
