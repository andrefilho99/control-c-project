package com.andrefilho99.controlCProject.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	
	public Information saveLimited(String value) {
		
		Information information = new Information();
		String hash = hashUtils.stringToSha1(value);
		String key = hash.substring(0, 6);
		
		information.setValue(value);
		information.setHash(hash);
		information.setKey(key);
		information.setIsLimited(true);
		information.setRemainingUses(5);
		
		informationRepository.save(information);
		
		return information;
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
		
		confirmUse(key);
		
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
	
	public void confirmUse(String key) {
		
		Information information = informationRepository.findByKey(key);
		
		if (information.getIsLimited()) {
			decrementInformation(information);
		} else {
		    Date date = new Date();
			information.setLastUse(date);
		}
		
	}
	
	private void decrementInformation(Information information) {
		
		int remainingUses = information.getRemainingUses();
		
		if (remainingUses <= 1) {
			informationRepository.delete(information);
		} else {
			information.setRemainingUses(remainingUses--);
		}	
	}
	
	public void clean() {
		List<Information> infos = informationRepository.findAll();
		
		for(Information info : infos) {
			
			if(lastUseThreeDaysAgo(info)) {
				informationRepository.delete(info);
			}
		}
	}
	
	public boolean lastUseThreeDaysAgo(Information info) {
		
		Calendar calendar = Calendar.getInstance();
		
		if(calendar.getTime().compareTo(info.getLastUse()) >= 3) {
			return true;
		}
		
		return false;
	}
}
