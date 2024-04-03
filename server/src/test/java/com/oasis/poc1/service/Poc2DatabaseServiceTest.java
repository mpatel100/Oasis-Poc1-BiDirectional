package com.oasis.poc1.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.oasis.poc1.entity.OasisPoc2;
import com.oasis.poc1.repository.OasisPoc2Repo;

@SpringBootTest
class Poc2DatabaseServiceTest {

	@Autowired
	private Poc2DatabaseService service;
	
	@MockBean
	private OasisPoc2Repo repository;
	
	List<OasisPoc2> entityList = new ArrayList<OasisPoc2>();
	
	@BeforeEach
	public void setUp() {
		//Mocking database entry
		OasisPoc2 entity1 = new OasisPoc2();
		entity1.setId(1);
		entity1.setLabelName("First Name");
		entity1.setEntityName("test");		
		entity1.setValue("John");
		entity1.setControlType("varchar");
		entity1.setAttribute("fname");
		entity1.setTooltip("Test Value for First Name");
		entity1.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
		
		entityList = List.of(entity1);	
		//Return mock data when find All method is called
		Mockito.when(repository.findAll()).thenReturn(entityList);
		//Return mock date when save method is called
		Mockito.when(repository.save(Mockito.any(OasisPoc2.class))).thenReturn(entity1);
	}
	
	/**************
	 * Method: testGetAllEntitiesFromTable 
	 * Purpose: This method will test for the entity values stored 
	 *          in Oasis_Poc2 based on the mock data
	 * Input parameters: None
	 */	
	@Test
	public void testGetAllEntitiesFromTable() {
		List<OasisPoc2> entityList = service.getAllEntitiesFromTable();
		//Testing response is not empty
		assertThat(entityList).isNotEmpty();
		//Testing response size is 1 (Mock data)
		assertEquals(entityList.size(), 1);
		//Testing response has label name as First Name
		assertEquals(entityList.get(0).getLabelName(), "First Name");
	}

	/**************
	 * Method: testUpdateDynamicTemplateEntities 
	 * Purpose: This method will test the entity values updated in Oasis_Poc2 Table as per mock data
	 * Input parameters: List<OasisPoc2>
	 *
	 */		
	@Test
	 public void testUpdateDynamicTemplateEntities() {
		 service.updateDynamicTemplateEntities(entityList);
		 //Verifying save() is invoked only once 
		 Mockito.verify(this.repository,Mockito.times(1)).save(Mockito.any(OasisPoc2.class));
		 
	 }
}
