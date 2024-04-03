package com.oasis.poc1.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.oasis.poc1.entity.OasisPoc1;
import com.oasis.poc1.repository.OasisPoc1Repo;

@SpringBootTest
class Poc1DatabaseServiceTest {

	@Autowired
	private Poc1DatabaseService service;
	
	@MockBean
	private OasisPoc1Repo repository;
	
	@BeforeEach
	void setUp() {
		//Mocking database entry
		OasisPoc1 entity1 = new OasisPoc1();
		entity1.setId(1);
		entity1.setEntityName("Test FName");
		entity1.setAttribute("fname");
		entity1.setLabelName("First Name");
		entity1.setTimeStamp(Instant.now().toString());
		entity1.setValue("John");
		
		OasisPoc1 entity2 = new OasisPoc1();
		entity2.setId(1);
		entity2.setEntityName("Test LName");
		entity2.setAttribute("lname");
		entity2.setLabelName("Last Name");
		entity2.setTimeStamp(Instant.now().toString());
		entity2.setValue("Doss");
		
		List<OasisPoc1> entityList = List.of(entity1,entity2);
		Mockito.when(repository.findAll()).thenReturn(entityList);
	}
	
	/**************
	 * Method: testCheckDatabaseConnection 
	 * Purpose: This method will test the Database Connection status is success
	 * Input parameters: None
	 * 
	 */
	@Test
	public void testCheckDatabaseConnection() throws SQLException {
		String response = service.checkDatabaseConnection();
		//Testing database connection response is not null & Empty
		assertThat(response).isNotEmpty();
		assertThat(response).isNotNull();
		//Testing for successful database connection
		assertEquals("Database is connected successfully", response);
	}
	
	/**************
	 * Method: testCheckDatabaseConnection 
	 * Purpose: This method will test the Database Connection status is success
	 * Input parameters: None
	 * 
	 */
	@Test
	public void testGetAllEntitiesfromDatabase(){
		List<OasisPoc1> entityList = new ArrayList<OasisPoc1>();
		entityList=repository.findAll();
		//Testing response is not empty
		assertThat(entityList).isNotEmpty();
		//Testing response has only 2 entries based on the mock data
		assertEquals(entityList.size(), 2);
		//Testing first name of the response 
		assertEquals(entityList.get(0).getValue(), "John");
		
	}

}
