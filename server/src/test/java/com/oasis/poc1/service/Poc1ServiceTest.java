package com.oasis.poc1.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.oasis.poc1.entity.TileSubsetQuery;
import com.oasis.poc1.entity.Token;
import com.oasis.poc1.entity.WellSubsetQuery;

@SpringBootTest
class Poc1ServiceTest {
	
	@Autowired
	private Poc1Service service;

	/**************
	 * Method: testGenerateTokenApi 
	 * Purpose: This method will test all success scenarios when receiving 
	 *          a token as response from ArcGis Enterprise API
	 * Input parameters: None
	 * 
	 */
	@Test
	public void testGetGenerateTokenApi() {
		ResponseEntity<Token> response= service.getGenerateTokenAPI();
		//Testing response json is not null
		assertThat(response.getBody()).isNotNull();		
		//Testing response json has no null fields
		assertThat(response.getBody()).hasNoNullFieldsOrProperties();
		//Testing response json is an instance of Token Class
		assertThat(response.getBody()).isInstanceOf(Token.class);
		//Testing response json has token field
		assertThat(response.getBody()).hasFieldOrProperty("token");
		//Testing token field is not null
		assertThat(response.getBody().getToken()).isNotNull();
		//Testing token is not empty
		assertThat(response.getBody().getToken()).isNotEmpty();
		//Testing response json has token expiry field
		assertThat(response.getBody()).hasFieldOrProperty("expires");
		//Testing token expires field is not null
		assertThat(response.getBody().getExpires()).isNotNull();
		//Testing response json  has token ssl field
		assertThat(response.getBody()).hasFieldOrProperty("ssl");
		//Testing ssl field is not null
		assertThat(response.getBody().isSsl()).isNotNull();
		//Testing ssl value is true
		assertEquals(true,response.getBody().isSsl());		
	}
	
	/**************
	 * Method: testGetQueryAllWells 
	 * Purpose: This method will test all success scenarios when receiving 
	 *         Well Query response from ArcGis Enterprise API
	 * Input parameters: None
	 * 
	 */
	@Test
	public void testGetQueryAllWells() {
		ResponseEntity<WellSubsetQuery> response = service.getQueryAllWells();
		//Testing response json is not null
		assertThat(response.getBody()).isNotNull();	
		//Testing response json has no null fields
		assertThat(response.getBody()).hasNoNullFieldsOrProperties();
		//Testing response json is an instance of WellSubsetQuery Class
		assertThat(response.getBody()).isInstanceOf(WellSubsetQuery.class);
	}

	/**************
	 * Method: testGetTileDrainageAreaSubsetQuery 
	 * Purpose: This method will test all success scenarios when receiving 
	 *         Tile Drainage Subset Query response from ArcGis Enterprise API
	 * Input parameters: None
	 * 
	 */
	@Test
	public void testGetTileDrainageAreaSubsetQuery() {
		ResponseEntity<TileSubsetQuery> response = service.getTileDrainageAreaSubsetQuery();
		//Testing response json is not null
		assertThat(response.getBody()).isNotNull();	
		//Testing response json has no null fields
		assertThat(response.getBody()).hasNoNullFieldsOrProperties();
		//Testing response json is an instance of TileSubsetQuery Class
		assertThat(response.getBody()).isInstanceOf(TileSubsetQuery.class);
	}

	
}
