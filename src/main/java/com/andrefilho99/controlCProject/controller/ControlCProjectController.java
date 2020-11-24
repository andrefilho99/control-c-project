package com.andrefilho99.controlCProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrefilho99.controlCProject.exceptions.InformationNotFoundException;
import com.andrefilho99.controlCProject.exceptions.MasterKeyNotFoundException;
import com.andrefilho99.controlCProject.model.MasterKey;
import com.andrefilho99.controlCProject.service.InformationService;
import com.andrefilho99.controlCProject.service.MasterKeyService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/")
public class ControlCProjectController {
	
	@Autowired
	private InformationService informationService;
	
	@Autowired
	private MasterKeyService masterKeyService;
	
	@PostMapping("create-master-key/{label}")
	public String createMasterKey(@PathVariable String label) {
		return masterKeyService.create(label);
	}
	
	@ExceptionHandler(MasterKeyNotFoundException.class)
	@GetMapping("paste-master-key/{key}")
	public MasterKey getMasterKey(@PathVariable String key) throws MasterKeyNotFoundException{
		return masterKeyService.getMasterKey(key);
	}
	
	@PostMapping("copy-to-key/{key}/{value}")
	public String copyToKey(@PathVariable String key, @PathVariable String value) throws Exception {
		return informationService.saveWithKey(value, key).getKey();
	}
	
	@PostMapping("copy-limited/{value}")
	public String copyLimited(@PathVariable String value) {
		return informationService.saveLimited(value).getKey();
	}
	
	@PostMapping("copy/{value}")
	public String copy(@PathVariable String value) {
		return informationService.save(value).getKey();
	}
	
	@ExceptionHandler(InformationNotFoundException.class)
	@GetMapping("paste/{key}")
	public String paste(@PathVariable String key) throws InformationNotFoundException {
		return informationService.getByKey(key).getValue();
	}
}
