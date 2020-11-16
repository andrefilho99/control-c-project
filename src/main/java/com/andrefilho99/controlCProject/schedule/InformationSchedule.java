package com.andrefilho99.controlCProject.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.andrefilho99.controlCProject.service.InformationService;

@Component
public class InformationSchedule {

	@Autowired
	private InformationService informationService;
	
	//Everyday at 7AM
	@Scheduled(cron = "* * 7 * * *")
	public void cleanInformation() {
		informationService.clean();
	}
}
