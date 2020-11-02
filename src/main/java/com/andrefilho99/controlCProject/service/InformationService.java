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
	
	public String save(String value) {
		
		Information checkInformation = informationAlreadyExists(value);
		
		if(checkInformation == null) {
			
			Information information = new Information();
			String hash = hashUtils.stringToSha1(value);
			String key = hash.substring(0, 6);
			
			information.setValue(value);
			information.setHash(hash);
			information.setKey(key);
			
			informationRepository.save(information);
			
			return key;
		} else {		
			return checkInformation.getKey();
		}
	}
	
	public String getByKey(String key) {
		
		Information information = informationRepository.findByKey(key);
		
		if(information == null) {
			return "This key doesn't correspond to any information";
		}
		
		return information.getValue();
	}
	
	private Information informationAlreadyExists(String value) {
		
		String hash = hashUtils.stringToSha1(value);
		Information information = informationRepository.findByHash(hash);
		
		if(information == null)
			return null;
		
		return information;
	}
}
