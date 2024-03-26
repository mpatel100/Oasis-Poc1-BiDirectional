package com.oasis.poc1.service;


import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.oasis.poc1.entity.TileSubsetQuery;
import com.oasis.poc1.entity.Token;
import com.oasis.poc1.entity.WellSubsetQuery;

/**************
 * Class: Poc1Service 
 * 
 * Purpose: Acts as a service layer to establish communication between ArcGIS Enterprise & Core Web.
 *     Poc1 - BiDirectional Communication - ArcGIS Enterprise to Core Web App
 */
@Service
public class Poc1Service {
	
	@Autowired
	RestTemplate restTemplate;
	
	Logger logger = LoggerFactory.getLogger(Poc1Service.class);
	
	String receivedToken = "";	
	
	/**************
	 * Method: getGenerateTokenAPI 
	 * Purpose: This method is used to receive a token as response from ArcGis Enterprise API
	 * Input parameters: None
	 * @return Token as response
	 */
	public ResponseEntity<Token> getGenerateTokenAPI(){			
		logger.info("**** inside getGenerateTokenAPI() begins ****");
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
		logger.info(" **** Response:" + response.getBody());
		}else {
			logger.error("ArcGIS Enterprise Generate Token API Call Failed : Empty Token");
		}
		
		receivedToken = response.getBody().getToken();
		
		logger.info("**** inside getGenerateTokenAPI() ends ****");
		return response;
	}

	/**************
	 * Method: getQueryAllWells 
	 * Purpose: This method is used to test Petroleum Well Subset API Query of ArcGIS Enterprise
	 * Input parameters: None
	 * @return PetroleumWell 
	 */
	public ResponseEntity<WellSubsetQuery> getQueryAllWells(){	
		
		logger.info("****  inside getQueryAllWells() begins ****");
		RestTemplate restTemplate = new RestTemplate();
		
		//Generates a token automatically.
		receivedToken = getGenerateTokenAPI().getBody().getToken();
		
		String PetroleumWellSubset = "https://services5.arcgis.com/D9dI3nY76wGaru7T/arcgis/rest/services/PETROLEUM_WELL_subset/FeatureServer";
		String tokenUrl= PetroleumWellSubset + "/query?token=" + receivedToken ;
		
		logger.info("ArcGIS Enterprise URL to getQueryAllWells " + tokenUrl);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		
		map.add("f", "json");
		map.add("layerDefs", "0:1=1");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<WellSubsetQuery> responseEntity =null;
		
		ResponseEntity<WellSubsetQuery> response = restTemplate.postForEntity(tokenUrl,request,WellSubsetQuery.class);
		if(Objects.nonNull(response)) {
			logger.info(" Response received successfully from ArcGIS Petroleum Well API : "+ response.getBody());
			WellSubsetQuery petroleumWell = response.getBody();
			logger.info(" **** Petroleum Well API Response:" + petroleumWell);
			responseEntity= ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(petroleumWell);
		}else {
			logger.error("..ArcGIS Enterprise Petroleum Well API Call Failed : Empty Response..");
			responseEntity=ResponseEntity.noContent().build();
		}
		logger.info("****  inside getQueryAllWells() ends ****");			
		return responseEntity;
		
	}
	
	
	/**************
	 * Method: getTileDrainageAreaSubsetQuery 
	 * Purpose: This method is used to test Tile Drainage Area Subset API Query of ArcGIS Enterprise
	 * Input parameters: None
	 * @return Tile Query response: TileSubsetQuery
	 */
	public ResponseEntity<TileSubsetQuery> getTileDrainageAreaSubsetQuery(){			
		logger.info("****  inside getTileDrainageAreaSubsetQuery() begins ****");		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<TileSubsetQuery> responseEntity =null;	
		
		if(receivedToken.isEmpty()) {
			logger.info("Token String is Empty. Generating Token");
			getGenerateTokenAPI();
			logger.info("Token String: " +  receivedToken);
		}
		String tileDrainageAreaSubset="https://services5.arcgis.com/D9dI3nY76wGaru7T/arcgis/rest/services/TILE_DRAINAGE_AREA_subset/FeatureServer";
		String tileUrl=tileDrainageAreaSubset+"/query?token="+receivedToken;   
				
		HttpHeaders header = new HttpHeaders();
	
		MultiValueMap<String,String> requestbody = new LinkedMultiValueMap<String,String>();		
		requestbody.add("f", "json");
		requestbody.add("layerDefs", "{\"6\": \"YEAR_OF_INSTALLATION = 2021\"}");
		
		HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String, String>>(requestbody,header);
		
		logger.info("Executing ArcGIS Enterprise Tile Drainage Area Subset API: " +tileUrl);
		
		ResponseEntity<TileSubsetQuery> response = restTemplate.postForEntity(tileUrl,request,TileSubsetQuery.class);;		
		if(Objects.nonNull(response)){
			TileSubsetQuery tileDrainageArea = response.getBody();					
			logger.info("*** Success Response received from ArcGIS Tile Drainage Area Subset API .." + tileDrainageArea);
			responseEntity= ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(tileDrainageArea);
		}else {
			logger.error("*** ArcGIS Enterprise Tile Drainage Area Subset Call Failed : Empty Response..");				
			responseEntity=ResponseEntity.noContent().build();
		}		
		logger.info("**** inside getTileDrainageAreaSubsetQuery() ends ****");			
		return responseEntity;			
	}
	
	
}
