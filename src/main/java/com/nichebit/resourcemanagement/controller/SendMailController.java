package com.nichebit.resourcemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.nichebit.resourcemanagement.dto.SendMailRequest;
import com.nichebit.resourcemanagement.service.SendMailService;

@RestController
public class SendMailController {
	
	@Autowired
	private SendMailService service;
	
	SendMailRequest sendMailRequest;
	
}
