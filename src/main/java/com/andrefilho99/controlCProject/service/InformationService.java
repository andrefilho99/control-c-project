package com.andrefilho99.controlCProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrefilho99.controlCProject.model.Information;
import com.andrefilho99.controlCProject.repository.InformationRepository;
import com.andrefilho99.controlCProject.utils.HashUtils;

@Service
public class InformationService {
	
	@Autowired
	private InformationRepository informationRepository;
	
	@Autowired
	private HashUtils hashUtils;
	
	public Information save(String value) {
		
		Information checkInformation = informationAlreadyExists(value);
		
		if(checkInformation == null) {
			
			Information information = new Information();
			String hash = hashUtils.stringToSha1(value);
			String key = hash.substring(0, 6);
			
			information.setValue(value);
			information.setHash(hash);
			information.setKey(key);
			
			informationRepository.save(information);
			
			return information;
		} else {		
			return checkInformation;
		}
	}
	
	public Information saveWithKey(String value, String masterKey) {
		
		Information checkInformation = informationAlreadyExists(value);
		
		if(checkInformation == null) {
			
			Information information = new Information();
			String hash = hashUtils.stringToSha1(value);
			String key = hash.substring(0, 6);
			
			information.setValue(value);
			information.setHash(hash);
			information.setKey(key);
			information.setMasterKey(masterKey);
			
			informationRepository.save(information);
			
			return information;
		} else {		
			return checkInformation;
		}
	}
	
	public Information getByKey(String key) throws Exception{
		
		Information information = informationRepository.findByKey(key);
		
		if(information == null) {
			throw new Exception("This key does not belong to any information.");
		}
		
		return information;
	}
	
	private Information informationAlreadyExists(String value) {
		
		String hash = hashUtils.stringToSha1(value);
		Information information = informationRepository.findByHash(hash);
		
		if(information == null)
			return null;
		
		return information;
	}
}
