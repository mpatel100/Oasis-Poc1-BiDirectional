package com.oasis.poc1.controller;

//import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oasis.poc1.entity.Token;
import com.oasis.poc1.service.Poc1Service;


@RestController
@RequestMapping("/oasis/poc1")
public class Poc1Controller {
	
	@Autowired
	Poc1Service service;
	
	@GetMapping("/hello-oasis")
	public String welcomeMessage() {
		return "Hello From Core Web Application, OASIS - POC-1.";		
	}
	
	@GetMapping("/getTokenAPI")
	public ResponseEntity<Token> getTokenAPI(){	
		ResponseEntity<Token> response= service.getGenerateTokenAPI();		
		return response;				
	}

//	@GetMapping("/getQueryAllWells")
//	public Object getQueryAllWells(){	
//		Object response= service.getQueryAllWells();		
//		return response;				
//	}	
}
