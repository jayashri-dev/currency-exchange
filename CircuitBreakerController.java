package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger =LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
	//@Retry(name="sample-api",fallbackMethod="hardcodedResponse")
	@CircuitBreaker(name="sample-api",fallbackMethod="hardcodedResponse")

	public String sampleApi() {
		logger.info("sample api call reveied");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http:local:8080/some", String.class);
		
		return forEntity.getBody();
	}
	public String hardcodedResponse( Exception ex) {
		return "fallback-response";
	}
}
