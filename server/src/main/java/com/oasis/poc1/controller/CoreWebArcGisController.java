package com.oasis.poc1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.poc1.entity.OasisPoc2;
import com.oasis.poc1.service.CoreWebArcGisService;

import io.swagger.v3.oas.annotations.tags.Tag;
/**************
 * Class: CoreWebArcGisController 
 * 
 * Purpose: Rest controller class that sends information from Core Web to ArcGIS Enterprise.
 *          Poc1 - BiDirectional Communication - Core Web to ArcGIS Enterprise
 */

@CrossOrigin(origins = {"https://oasis-poc2-dynamicui.azurewebsites.net","http://localhost:4200"})
@Tag(name = "Poc 1 - BiDirectional Communication", description = "CoreWeb App - ArcGIS Communucation")
@RestController
@RequestMapping("/oasis/poc1")
public class CoreWebArcGisController {
	
	@Autowired
	CoreWebArcGisService service;
	
	/**************
	 * Method: getCoreWebArcGisCommunication 
	 * Purpose: This method will return updated UI form elements in the response
	 * Input parameters: None
	 * @return OutputJson
	 */
	@GetMapping("/getCoreWebArcGisCommunication")
	public ResponseEntity<List<OasisPoc2>> getCoreWebArcGisCommunication(){
		ResponseEntity<List<OasisPoc2>> responseEntity=service.getCoreWebArcGisCommunication();
		return responseEntity;	
	}
	
}