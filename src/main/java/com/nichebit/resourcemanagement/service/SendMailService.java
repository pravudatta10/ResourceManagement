package com.nichebit.resourcemanagement.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.nichebit.resourcemanagement.dto.EmployeeRequest;
import com.nichebit.resourcemanagement.dto.SendMailRequest;
import com.nichebit.resourcemanagement.dto.SendMailResponse;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	public SendMailResponse sendMail(SendMailRequest sendMailRequest, Map<String, Object> model)
			throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException,
			IOException, TemplateException {
		SendMailResponse sendMailResponse = new SendMailResponse();
		MimeMessage message = javaMailSender.createMimeMessage();

		// set mediaType
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		// add attachment
		helper.addAttachment("Welcome.docx", new ClassPathResource("assets/Welcome.docx"));
		freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
		Template template = configuration.getTemplate("registerTemplate.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

		helper.setTo(sendMailRequest.getTo());
		helper.setSubject(sendMailRequest.getSubject());
		helper.setText(html, true);
		helper.setFrom(sendMailRequest.getFrom());
	
		javaMailSender.send(message);

		sendMailResponse.setStatus("Mail Send to" + sendMailRequest.getTo());

		return sendMailResponse;
	}
}
