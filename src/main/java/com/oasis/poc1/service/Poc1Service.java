package com.oasis.poc1.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.oasis.poc1.entity.AllWells;
import com.oasis.poc1.entity.Token;



@Service
public class Poc1Service {
	
	@Autowired
	RestTemplate restTemplate;
	
	Logger logger = LoggerFactory.getLogger(Poc1Service.class);
	
	String receivedToken = "";
	
	
	//Testing REST API using Rest Template 
//	public ResponseEntity<?> testExternalGetRestAPIUsingRestTemmplate(String url){
//		
//		logger.info("..Testing ArcGIS Enterprise Rest API: " +url+ " begins..");
//		ResponseEntity<?> responseEntity =null;
//		Object response=restTemplate.getForObject(url, Object.class);		
//		if(Objects.nonNull(response)) {
//			logger.info("..Success Response from ArcGIS Enterprise Rest API ..");
//			logger.info("Response:" + response);
//			responseEntity= ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(response);
//		}else {
//			logger.info("..ArcGIS Enterprise Rest API Call Failed : Empty Response..");
//			responseEntity=ResponseEntity.noContent().build();
//		}
//		logger.info("..Testing ArcGIS Enterprise Rest API "+url+ " ends..");
//		return responseEntity;
//	}


    //Testing POST API using Rest Template 
	public ResponseEntity<Token> getGenerateTokenAPI(){			
		logger.info("inside getGenerateTokenAPI()");
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://lrcgo.maps.arcgis.com/sharing/generateToken";
		logger.info("*** ArcGIS Enterprise URL to Generate Token API: " + url);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("username", "dev_automation");
		map.add("password", "OASISpoc13");
		map.add("f", "json");
		map.add("referer", "https://www.arcgis.com");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<Token> response = restTemplate.postForEntity(url, request , Token.class);
		if(Objects.nonNull(response)) {
		logger.info("Token received successfully from ArcGIS Enterprise Generate Token API");
		logger.info("Response:" + response.getBody());
		}else {
			logger.error("ArcGIS Enterprise Generate Token API Call Failed : Empty Token");
		}
		
		receivedToken = response.getBody().getToken();
		
		logger.info("end of getGenerateTokenAPI()");
		return response;
	}

    //Testing POST API using Rest Template 
	public Object getQueryAllWells(){			
		logger.info("inside getQueryAllWells()");
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://services5.arcgis.com/D9dI3nY76wGaru7T/arcgis/rest/services/PETROLEUM_WELL_subset/FeatureServer";
		
		logger.info("ArcGIS Enterprise URL to getQueryAllWells " + url);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("token", receivedToken);
		map.add("f", "json");
		map.add("layerDefs", "0:1=1");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		Object x = new Object();
		
		//////// TODO
		AllWells response = restTemplate.postForObject(url, request, AllWells.class);
		
		logger.info("**************** AllWells  :" + response);
		
//		ResponseEntity<Token> response = restTemplate.postForEntity(url, request , Token.class);
//		if(Objects.nonNull(response)) {
//		logger.info("Token received successfully from ArcGIS Enterprise Generate Token API");
//		logger.info("Response:" + response.getBody());
//		}else {
//			logger.error("ArcGIS Enterprise Generate Token API Call Failed : Empty Token");
//		}
//		
//		receivedToken = response.getBody().getToken();
//		
//		logger.info("Testing ArcGIS Enterprise Generate Token API "+ url + " ends");
		return response;
	}
	
	
}
