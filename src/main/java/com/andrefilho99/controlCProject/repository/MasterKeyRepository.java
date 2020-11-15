package com.andrefilho99.controlCProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.andrefilho99.controlCProject.model.MasterKey;

@Repository
public interface MasterKeyRepository extends JpaRepository<MasterKey, Integer>{
	
	@Query(value = "select * from master_key where key = ?1", nativeQuery=true)
	public MasterKey findByKey(String key);
}
