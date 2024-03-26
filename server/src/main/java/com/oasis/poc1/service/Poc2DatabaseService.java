package com.oasis.poc1.service;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasis.poc1.entity.OasisPoc2;
import com.oasis.poc1.repository.OasisPoc2Repo;

/**************
 * Class: Poc2DatabaseService 
 * 
 * Purpose: This class will have functions that communicates with Oasis_Poc2 DB Table.
 *          Oasis_Poc2 : Dynamic UI Template Control
 */

@Service
public class Poc2DatabaseService {
	
	@Autowired
	OasisPoc2Repo repository;
	
	Logger logger = LoggerFactory.getLogger(Poc2DatabaseService.class);
	
	/**************
	 * Method: getAllEntitiesFromTable 
	 * Purpose: This method will return all the entity values store in Oasis_Poc2 Table
	 * Input parameters: None
	 * @return List <Oasis_Poc2>
	 */	
	public List<OasisPoc2> getAllEntitiesFromTable(){
		logger.info("**** inside getAllEntitiesFromTable() begins ****");
		List<OasisPoc2> entityList = new ArrayList<OasisPoc2>();
		entityList = repository.findAll();
		if(Objects.nonNull(entityList)&& entityList.size()!=0) {
			logger.info("**** Respose : "+ entityList.toString()+" ****");
		}else {
			logger.error("Entity List is empty is Database");
		}
		logger.info("**** inside getAllEntitiesFromTable() ends ****");
		return entityList;
	}
	
	/**************
	 * Method: updateDynamicTemplateEntities 
	 * Purpose: This method will update the entity values stored in Oasis_Poc2 Table as per the changes in UI
	 * Input parameters: List<OasisPoc2>
	 * @return none
	 */		
	public void updateDynamicTemplateEntities(List<OasisPoc2> entityList) {
		
		logger.info("**** inside updateDynamicTemplateEntities() begins *****");
		if(Objects.nonNull(entityList) && entityList.size()>0) {
			
			for (OasisPoc2 entity: entityList) {
				//Setting timestamp before saving
				entity.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
				repository.save(entity);				
			}
			logger.info("All template entities are updated in DB :"+ entityList.toString());
		}else {
			logger.info("Dynamic Template Entity List is empty");
		}
		logger.info("***** inside updateDynamicTemplateEntities() ends *****");
	}

}