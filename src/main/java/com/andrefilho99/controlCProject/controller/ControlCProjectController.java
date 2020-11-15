package com.andrefilho99.controlCProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("create-key/{label}")
	public String createMasterKey(@PathVariable String label) {
		return masterKeyService.create(label);
	}
	
	@GetMapping("copy-to-key/{key}/{value}")
	public String copy(@PathVariable String key, @PathVariable String value) {
		return masterKeyService.addInfo(key, value);
	}
	
	@GetMapping("copy/{value}")
	public String copy(@PathVariable String value) {
		return informationService.save(value).getKey();
	}
	
	@GetMapping("paste/{key}")
	public String paste(@PathVariable String key) {
		try {
			return informationService.getByKey(key).getValue();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
