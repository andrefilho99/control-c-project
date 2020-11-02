package com.andrefilho99.controlCProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrefilho99.controlCProject.service.InformationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/")
public class InformationController {
	
	@Autowired
	private InformationService informationService;
	
	@GetMapping("copy/{value}")
	public String copy(@PathVariable String value) {
		return informationService.save(value);
	}
	
	@GetMapping("paste/{key}")
	public String paste(@PathVariable String key) {
		return informationService.getByKey(key);
	}
}
