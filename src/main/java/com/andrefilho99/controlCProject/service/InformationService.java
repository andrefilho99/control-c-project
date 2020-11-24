package com.andrefilho99.controlCProject.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrefilho99.controlCProject.exceptions.InformationNotFoundException;
import com.andrefilho99.controlCProject.model.Information;
import com.andrefilho99.controlCProject.model.MasterKey;
import com.andrefilho99.controlCProject.repository.InformationRepository;
import com.andrefilho99.controlCProject.utils.HashUtils;

@Service
public class InformationService {
	
	@Autowired
	private InformationRepository informationRepository;
	
	@Autowired
	private MasterKeyService masterKeyService;
	
	@Autowired
	private HashUtils hashUtils;
	
	private Information setInformation(String value) {
		
		Information information = new Information();
		String hash = hashUtils.stringToSha1(value);
		String key = hash.substring(0, 6);
		
		information.setValue(value);
		information.setHash(hash);
		information.setKey(key);
		
		return information;
	}
	
	public Information save(String value) {
		
		Information checkInformation = informationAlreadyExists(value);
		
		if(checkInformation == null) {
			
			Information information = setInformation(value);
			informationRepository.save(information);
			
			return information;
		} else {		
			return checkInformation;
		}
	}
	
	public Information saveLimited(String value) {
		
		Information information = setInformation(value);
		
		information.setIsLimited(true);
		information.setRemainingUses(5);
		
		informationRepository.save(information);
		
		return information;
	}
	
	public Information saveWithKey(String value, String masterKey) throws Exception {
		
		MasterKey checkMasterKey = masterKeyService.getMasterKey(masterKey);
		Information information = setInformation(value);
		
		information.setMasterKey(checkMasterKey);
		
		informationRepository.save(information);
		
		return information;
	}
	
	
	
	public Information getByKey(String key) throws InformationNotFoundException{
		
		Information information = informationRepository.findByKey(key);
		
		if(information == null) {
			throw new InformationNotFoundException("The given key does not belong to any information.");
		}
		
		confirmUse(information);
		
		return information;
	}
	
	private Information informationAlreadyExists(String value) {
		
		String hash = hashUtils.stringToSha1(value);
		Information information = informationRepository.findByHash(hash);
		
		if(information == null)
			return null;
		
		return information;
	}
	
	public void confirmUse(Information information) {
		
		if (information.getIsLimited()) {
			decrementInformation(information);
		} else {
		    Date date = new Date();
			information.setLastUse(date);
		}
	}
	
	private void decrementInformation(Information information) {
		
		if (information.getRemainingUses() <= 1) {
			informationRepository.delete(information);
		} else {
			information.setRemainingUses(information.getRemainingUses() - 1);
			informationRepository.save(information);
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
