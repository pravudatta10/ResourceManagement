package com.nichebit.resourcemanagement.service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import com.nichebit.resourcemanagement.dto.SendMailRequest;
import com.nichebit.resourcemanagement.dto.SendMailResponse;

import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	

	public SendMailResponse sendMail(SendMailRequest sendMailRequest, Map<String, String> model, Template template)
			throws Exception {
		SendMailResponse sendMailResponse = new SendMailResponse();
		try {

			MimeMessage message = javaMailSender.createMimeMessage();

			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment
			// helper.addAttachment("Welcome.docx", new
			// ClassPathResource("assets/Welcome.docx"));

			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

			helper.setTo(sendMailRequest.getTo());
			helper.setSubject(sendMailRequest.getSubject());
			helper.setText(html, true);
			helper.setFrom(sendMailRequest.getFrom());

			javaMailSender.send(message);

			sendMailResponse.setStatus("Mail Send to" + sendMailRequest.getTo());

			return sendMailResponse;
		} catch (Exception e) {
			sendMailResponse.setStatus("Unable to Send Mail" + sendMailRequest.getTo());
			return sendMailResponse;
		}
	}
}
