package com.cc.controller;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/resources",glue = {"com.cc.StepDefinations"})
public class CreditCardCucumberController {

}
