package com.in28minutes.microservices.currencyexchangeservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.in28minutes.microservices.currencyexchangeservice.CurrencyExchange;

public interface CurrencyExchangeRepository 
	extends JpaRepository<CurrencyExchange, Long> {
	
	 @Query("SELECT c FROM CurrencyExchange c WHERE c.from = :from AND c.to = :to")
	   CurrencyExchange findByFromAndTo(@Param("from") String from, @Param("to") String to);
	}
