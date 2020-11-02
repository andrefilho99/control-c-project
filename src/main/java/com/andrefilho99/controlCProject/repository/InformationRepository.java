package com.andrefilho99.controlCProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.andrefilho99.controlCProject.model.Information;

@Repository
public interface InformationRepository extends JpaRepository<Information, Integer>{

	@Query(value = "select * from information where hash = ?1", nativeQuery=true)
	public Information findByHash(String hash);
	
	@Query(value = "select * from information where key = ?1", nativeQuery=true)
	public Information findByKey(String key);
}
