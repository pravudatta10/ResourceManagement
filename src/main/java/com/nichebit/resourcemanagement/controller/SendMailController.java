package com.nichebit.resourcemanagement.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nichebit.resourcemanagement.dto.EmployeeResponse;
import com.nichebit.resourcemanagement.dto.SendMailRequest;
import com.nichebit.resourcemanagement.dto.SendMailResponse;
import com.nichebit.resourcemanagement.service.SendMailService;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import jakarta.mail.MessagingException;

@RestController
public class SendMailController {
	
	@Autowired
	private SendMailService service;
	
	SendMailRequest sendMailRequest;
	/*@PostMapping("/sendmail")
	public SendMailResponse sendEmail(@RequestBody SendMailRequest sendMailRequest,Template template) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Map<String, String> model = new HashMap<>();
		//ClassPathResource logoResouce=new ClassPathResource("assets/nblogo.png");
		String logoPath = "cid:assets/nblogo.png"; // Use the 'cid' approach to reference the attachment in the email content
		model.put("UserName",sendMailRequest.getName());
		model.put("Password",sendMailRequest.getPassword());
		model.put("logoPath","/ResourceManagement/src/main/resources/assets/nblogo.png" );
		return service.sendMail(sendMailRequest, model,template);
	}*/
}
