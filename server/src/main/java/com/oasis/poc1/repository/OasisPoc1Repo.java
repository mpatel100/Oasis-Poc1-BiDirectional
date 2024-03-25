package com.oasis.poc1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oasis.poc1.entity.OasisPoc1;

/**************
 * Interface: OasisPoc1Repo 
 * 
 * Purpose: This class will act as a database layer of Oasis_Poc1 DB 
 * and contains method to access all database functions.
 * 
 */
@Repository
public interface OasisPoc1Repo extends JpaRepository<OasisPoc1, Integer>{

}
