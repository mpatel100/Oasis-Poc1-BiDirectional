package com.oasis.poc1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.poc1.entity.TileSubsetQuery;
import com.oasis.poc1.entity.Token;
import com.oasis.poc1.entity.WellSubsetQuery;
import com.oasis.poc1.service.Poc1Service;

import io.swagger.v3.oas.annotations.tags.Tag;

/**************
 * Class: Poc1Controller 
 * 
 * Purpose: Rest controller class to test External Rest API of ArcGIS Enterprise
 * Poc1 - BiDirectional Communication - ArcGIS to Core Web
 */

@Tag(name = "OASIS - POC 1", description = "Core Web - ArcGIS Communucation")
@CrossOrigin(origins = {"https://oasis-poc2-dynamicui.azurewebsites.net","http://localhost:4200"})
@RestController
@RequestMapping("/oasis/poc1")
public class Poc1Controller {
	
	@Autowired
	Poc1Service service;
	
	Logger logger = LoggerFactory.getLogger(Poc1Service.class);
	
	/**************
	 * Method: welcomeMessage 
	 * Purpose: This method will return a welcome string message as response
	 * Input parameters: None
	 * @return String
	 */	
	@GetMapping("/hello-oasis")
	public String welcomeMessage() {
		return "Hello From Core Web Application, OASIS - POC-1.";		
	}
	
	/**************
	 * Method: getTokenAPI 
	 * Purpose: This method is used to receive a token from ArcGis Enterprise
	 * Input parameters: None
	 * @return Token as response
	 */
	@GetMapping("/getTokenAPI")
	public ResponseEntity<Token> getTokenAPI(){	
		ResponseEntity<Token> response= service.getGenerateTokenAPI();
		
		return response;				
	}

	/**************
	 * Method: getQueryAllWells 
	 * Purpose: This method is used to test Petroleum Well Subset API Query of ArcGIS Enterprise
	 * Input parameters: None
	 * @return PetroleumWell
	 */
	@GetMapping("/getQueryAllWells")
	public ResponseEntity<WellSubsetQuery> getQueryAllWells(){			
		ResponseEntity<WellSubsetQuery> responseEntity= service.getQueryAllWells();		
		return responseEntity;				
	}	
	
	/**************
	 * Method: getTileDrainageAreaApi 
	 * Purpose: This method is used to test Tile Drainage Area Subset API Query of ArcGIS Enterprise
	 * Input parameters: None
	 * @return Tile Query response: TileSubsetQuery
	 */
	@GetMapping("/getTileQuery")
	public ResponseEntity<TileSubsetQuery> getTileDrainageAreaApi(){	
		ResponseEntity<TileSubsetQuery> responseEntity=service.getTileDrainageAreaSubsetQuery();		
		return responseEntity;				
	}
}
