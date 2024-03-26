package com.oasis.poc1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.poc1.entity.OasisPoc2;
import com.oasis.poc1.service.Poc2DatabaseService;

/**************
 * Class: Poc2DatabaseController 
 * 
 * Purpose: Rest controller class that has functions to communicates with Oasis_Poc2 Table.
 *          Oasis_Poc2 : Dynamic UI Template Control
 * 
 */

@CrossOrigin(origins = {"https://oasis-poc2-dynamicui.azurewebsites.net","http://localhost:4200"})
@RestController
@RequestMapping("/oasis/poc2")
public class Poc2DatabaseController {

	@Autowired
	Poc2DatabaseService service;
	
	Logger logger = LoggerFactory.getLogger(Poc2DatabaseController.class);
	
	/**************
	 * Method: getAllEntitiesFromTable 
	 * Purpose: This method will return all the entities store in Oasis_Poc2 Table
	 * Input parameters: None
	 * @return List<Oasis_Poc2>
	 */
	@GetMapping("/getAllPoc2Entities")
	public List<OasisPoc2> getAllEntitiesFromTable(){
		logger.info("**** inside getAllEntitiesFromTable() begins");
		List<OasisPoc2> entityList = service.getAllEntitiesFromTable();
		logger.info("**** inside getAllEntitiesFromTable() ends");
		return entityList;
	}
	
	/**************
	 * Method: updateDynamicTemplateEntities 
	 * Purpose: This method will update the entities stored in Oasis_Poc2 Table based on the information entered in frontend
	 * Input parameters: List<OasisPoc2>
	 * @return void
	 */
	@PutMapping("/updateDynamicEntity")
	public void updateDynamicTemplateEntities(@RequestBody List<OasisPoc2> poc2EntityList){
		logger.info("**** inside updateDynamicTemplateEntities() begins");
		service.updateDynamicTemplateEntities(poc2EntityList);
		logger.info("**** inside updateDynamicTemplateEntities() ends");
	}
}
