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
		//Testing JSON response is not null
		assertThat(response.getBody()).isNotNull();		
		//Testing JSON response has no null fields
		assertThat(response.getBody()).hasNoNullFieldsOrProperties();
		//Testing JSON response is an instance of Token Class
		assertThat(response.getBody()).isInstanceOf(Token.class);
		//Testing JSON response has token field
		assertThat(response.getBody()).hasFieldOrProperty("token");
		//Testing token field is not null
		assertThat(response.getBody().getToken()).isNotNull();
		//Testing token is not empty
		assertThat(response.getBody().getToken()).isNotEmpty();
		//Testing JSON response has token expire field
		assertThat(response.getBody()).hasFieldOrProperty("expires");
		//Testing token expires field is not null
		assertThat(response.getBody().getExpires()).isNotNull();
		//Testing JSON response has SSL field
		assertThat(response.getBody()).hasFieldOrProperty("ssl");
		//Testing SSL field is not null
		assertThat(response.getBody().isSsl()).isNotNull();
		//Testing SSL value is true
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
		//Testing JSON response is not null
		assertThat(response.getBody()).isNotNull();	
		//Testing JSON response is an instance of WellSubsetQuery Class
		assertThat(response.getBody()).isInstanceOf(WellSubsetQuery.class);
		//Testing JSON response  has Id as 0
		assertEquals(0, response.getBody().getLayers().get(0).getId());
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
		//Testing JSON response is not null
		assertThat(response.getBody()).isNotNull();	
		//Testing JSON response is an instance of TileSubsetQuery Class
		assertThat(response.getBody()).isInstanceOf(TileSubsetQuery.class);
		//Testing JSON response has Id as 6
		assertEquals(6, response.getBody().getLayers().get(0).getId());
	}

}
