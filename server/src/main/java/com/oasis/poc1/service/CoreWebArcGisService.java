package com.oasis.poc1.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oasis.poc1.entity.OasisPoc2;


/**************
 * Class: CoreWebArcGisService 
 * 
 * Purpose: Acts as a service layer that sends information from Core Web to ArcGIS Enterprise.
 *     Poc1 - BiDirectional Communication - Core Web to ArcGIS Enterprise
 */

@Service
public class CoreWebArcGisService {
	/*
	@Autowired
	ResourceLoader resourceLoader;
	*/
	
	@Autowired
	Poc2DatabaseService poc2DbService;
	
	Logger logger = LoggerFactory.getLogger(CoreWebArcGisService.class);
	
	/**************
	 * Method: getCoreWebArcGisCommunication 
	 * Purpose: This method will check for the updated data file 
	 *          and read its contents and send it back as response        
	 * Input parameters: None
	 * @return OutputJson[]
	 */
	 public ResponseEntity<List<OasisPoc2>> getCoreWebArcGisCommunication(){
		logger.info("**** inside getCoreWebArcGisCommunication() begins ****");
		ResponseEntity<List<OasisPoc2>> responseEntity=null;			
		List<OasisPoc2> entityList = poc2DbService.getAllEntitiesFromTable();
		if(entityList.size()>0) {	
			logger.info("**** Response: "+ entityList.toString()+ "****");
			responseEntity = ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(entityList);
		}else {
			logger.error(" Entity List in empty is database ");
			responseEntity = ResponseEntity.noContent().build();
		}
		logger.info("**** inside getCoreWebArcGisCommunication() ends ****");
		return responseEntity;	
	}
	
	/*
	public ResponseEntity<OutputJson[]> getCoreWebArcGisCommunication(){
		logger.info("CoreWebArcGisService - getCoreWebArcGisCommunication() begins");
		ResponseEntity<OutputJson[]> responseEntity=null;		
		try {
			String home = System.getProperty("user.home");
			String filepath = home+"/Downloads/Output.json";
			File file = new File(filepath);
			String fileContent ="";
			if(file.exists() && file.canRead()) {
				//Reading Output.JSON file from Downloads path if file exists
				 fileContent = new String(Files.readAllBytes(Paths.get(filepath)));				
			}else {
				//Reading Output.JSON file in Resource folder 
				Resource resource = resourceLoader.getResource("classpath:output.json");
				fileContent = new String(resource.getInputStream().readAllBytes());
			}
			if(Objects.nonNull(fileContent)&&!fileContent.isEmpty()) {
				ObjectMapper objectMapper = new ObjectMapper();
				OutputJson[] response = objectMapper.readValue(fileContent, OutputJson[].class);
				logger.info("**** Response : "+ Arrays.toString(response) + "****");
				responseEntity = ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(response);
			}else {
				responseEntity = ResponseEntity.noContent().build();
			}
		} catch (IOException e) {
			logger.error("Error in reading JSON file: "+ e.getMessage());			
		}
		logger.info("CoreWebArcGisService - getCoreWebArcGisCommunication() ends");
		return responseEntity;	
	}*/
	
	
}