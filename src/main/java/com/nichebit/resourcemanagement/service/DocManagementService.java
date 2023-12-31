package com.nichebit.resourcemanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nichebit.resourcemanagement.dto.DocManagementRequest;
import com.nichebit.resourcemanagement.dto.DocManagementResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.entity.DocManagement;
import com.nichebit.resourcemanagement.repository.DocManagentRepository;

import lombok.Data;

@Service
@Data
public class DocManagementService {

	@Autowired
	DocManagentRepository docManagentRepository;

	@Autowired
	UtilitysServices utilitysServices;

	@Autowired
	private ModelMapper modelMapper;

	DocManagementRequest docManagementRequest = new DocManagementRequest();
	ReturnResponse returnResponse = new ReturnResponse();

	public DocManagementResponse saveDocDetail(DocManagementRequest docManagementRequest) {
		DocManagement docManagement = this.modelMapper.map(docManagementRequest, DocManagement.class);
		docManagentRepository.save(docManagement);
		return this.modelMapper.map(docManagement, DocManagementResponse.class);
	}

	public ResponseEntity<DocManagement> updateDocDetail(DocManagementRequest docManagementRequest) {
		DocManagement docManagement = docManagentRepository.findById(docManagementRequest.getId()).orElse(null);
		if (docManagement == null) {
			return ResponseEntity.notFound().build();
		}
		docManagement = this.modelMapper.map(docManagementRequest, DocManagement.class);
		docManagentRepository.save(docManagement);
		return ResponseEntity.ok(docManagement);
	}

	public ResponseEntity<ReturnResponse> deleteDocDetail(Long id) {
		DocManagement docManagement = docManagentRepository.findById(id).orElse(null);
		if (docManagement == null) {

			docManagementRequest.setStatus("Document  not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			docManagentRepository.deleteById(id);
			returnResponse.setStatus("Deleted Successfully");
			return ResponseEntity.ok(returnResponse);
		}

	}

	public List<DocManagementResponse> getdocDetails() {

		return docManagentRepository.findAll().stream()
				.map(docManagement -> new DocManagementResponse(docManagement.getId(), docManagement.getDocname(),
						docManagement.getDocpath(), docManagement.getDoctype(), docManagement.getEntityname(),
						docManagement.getEntitygeneratedid(), docManagement.getUploadedby(),
						docManagement.getUploadedon(), docManagement.getRemarks()))
				.toList();
	}

	@Value("${server.path}")
	String folderPath;

	public DocManagementRequest uploadDoc(MultipartFile multipartFile) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(0));

		try {
			String filename = multipartFile.getOriginalFilename();
			if (filename != null) {
				int index = filename.lastIndexOf('.');
				if (index >= 0) {
					String partBeforeLastDot = filename.substring(0, index);
					String partAfterLastDot = filename.substring(index + 1);
					String ext = filename.substring(index + 1);
					String docpath = folderPath + partBeforeLastDot + timestamp + "." + partAfterLastDot;
					multipartFile.transferTo(new File(docpath));
					docManagementRequest.setDocname(filename);
					docManagementRequest.setDocpath(docpath);
					docManagementRequest.setDoctype(ext);
				}
			}
			return docManagementRequest;
		} catch (Exception e) {
			e.printStackTrace();
			docManagementRequest = new DocManagementRequest();
			return docManagementRequest;
		}
	}

	public byte[] downloadDoc(String name) {
		try {
			Optional<DocManagement> docData = docManagentRepository.findBydocname(name);
			if (docData.isPresent()) {
				String filePath = docData.get().getDocpath();
				return Files.readAllBytes(new File(filePath).toPath());
			} else {
				return new byte[0];
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	public byte[] downloadexcel(String name, int financialyear, String month) {
		try {
			String filePath = utilitysServices.excelForTimeSheet(name, financialyear, month);

			return Files.readAllBytes(new File(filePath).toPath());

		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

	public List<DocManagementResponse> getDocDetailsByid(String entityname, String entitygeneratedid) {

		return docManagentRepository.findDocByentityid(entityname, entitygeneratedid).stream()
				.map(docManagement -> new DocManagementResponse(docManagement.getId(), docManagement.getDocname(),
						docManagement.getDocpath(), docManagement.getDoctype(), docManagement.getEntityname(),
						docManagement.getEntitygeneratedid(), docManagement.getUploadedby(),
						docManagement.getUploadedon(), docManagement.getRemarks()))
				.toList();
	}

}
