package com.oasis.poc1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.poc1.entity.PetroleumWell;
import com.oasis.poc1.entity.Token;
import com.oasis.poc1.service.Poc1Service;


@RestController
@RequestMapping("/oasis/poc1")
public class Poc1Controller {
	
	@Autowired
	Poc1Service service;
	
	Logger logger = LoggerFactory.getLogger(Poc1Service.class);
	
		
	@GetMapping("/hello-oasis")
	public String welcomeMessage() {
		return "Hello From Core Web Application, OASIS - POC-1.";		
	}
	
	@GetMapping("/getTokenAPI")
	public ResponseEntity<Token> getTokenAPI(){	
		ResponseEntity<Token> response= service.getGenerateTokenAPI();
		
		return response;				
	}

	@GetMapping("/getQueryAllWells")
	public ResponseEntity<PetroleumWell> getQueryAllWells(){			
		ResponseEntity<PetroleumWell> responseEntity= service.getQueryAllWells();		
		return responseEntity;				
	}	
}
