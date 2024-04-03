package com.oasis.poc1.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.oasis.poc1.entity.OasisPoc2;

@SpringBootTest
class CoreWebArcGisServiceTest {
	
	@Autowired
	private CoreWebArcGisService service;


	/**************
	 * Method: testGetCoreWebArcGisCommunication 
	 * Purpose: This method will test the communication between Core Web & ArcGIS Enterprise
	 *          based on success scenarios         
	 * Input parameters: None
	 * 
	 */
	 @Test
	 public void testGetCoreWebArcGisCommunication(){
		 ResponseEntity<List<OasisPoc2>> responseEntity = service.getCoreWebArcGisCommunication();
		 //Testing response is not Empty
		 assertThat(responseEntity.getBody()).isNotEmpty();		
		 //Testing response is not Null
		 assertThat(responseEntity.getBody()).isNotNull();
		 //Testing Label name is "First Name" for the first row of the response
		 assertEquals("First Name", responseEntity.getBody().get(0).getLabelName());
	 }
	
	
}
