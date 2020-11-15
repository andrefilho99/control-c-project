package com.andrefilho99.controlCProject.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrefilho99.controlCProject.model.Information;
import com.andrefilho99.controlCProject.model.MasterKey;
import com.andrefilho99.controlCProject.repository.MasterKeyRepository;
import com.andrefilho99.controlCProject.utils.HashUtils;

@Service
public class MasterKeyService {
	
	@Autowired
	private MasterKeyRepository masterKeyRepository;
	
	@Autowired
	private InformationService informationService;
	
	@Autowired
	private HashUtils hashUtils;
	
	public String create(String label) {
		
		MasterKey masterKey = new MasterKey();
		Long milisseconds = new Date().getTime();
		String hash = hashUtils.stringToSha1(milisseconds.toString());
		String key = hash.substring(0, 6);
		
		masterKey.setKey(key);
		masterKey.setLabel(label);
		masterKeyRepository.save(masterKey);
		
		return key;
	}
	
	public String addInfo(String masterKey, String value){
		
		try {
			Information info = informationService.saveWithKey(value, masterKey);
			return info.getKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
