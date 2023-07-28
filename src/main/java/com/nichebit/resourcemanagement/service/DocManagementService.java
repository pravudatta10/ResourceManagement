package com.nichebit.resourcemanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nichebit.resourcemanagement.dto.DocManagementRequest;
import com.nichebit.resourcemanagement.dto.DocManagementResponse;
import com.nichebit.resourcemanagement.entity.DocManagement;
import com.nichebit.resourcemanagement.repository.DocManagentRepository;

import lombok.Builder;
import lombok.Data;

@Service
@Data
public class DocManagementService {

	@Autowired
	DocManagentRepository docManagentRepository;

	public String saveDocDetail(DocManagementRequest docManagementRequest) {
		DocManagement docManagement = new DocManagement();
		docManagement.setDocname(docManagementRequest.getDocname());
		docManagement.setDocpath(docManagementRequest.getDocpath());
		docManagement.setDoctype(docManagementRequest.getDoctype());
		docManagement.setEntityname(docManagementRequest.getEntityname());
		docManagement.setEntitygeneratedid(docManagementRequest.getEntitygeneratedid());
		docManagement.setUploadedby(docManagementRequest.getUploadedby());
		docManagement.setUploadedon(docManagementRequest.getUploadedon());
		docManagement.setRemarks(docManagementRequest.getRemarks());
		docManagentRepository.save(docManagement);
		return "Document Uploaded  Successfully";
	}

	public String updateDocDetail(DocManagementRequest docManagementRequest) {
		DocManagement docManagement = docManagentRepository.findById(docManagementRequest.getId()).orElse(null);
		docManagement.setDocname(docManagementRequest.getDocname());
		docManagement.setDocpath(docManagementRequest.getDocpath());
		docManagement.setDoctype(docManagementRequest.getDoctype());
		docManagement.setEntityname(docManagementRequest.getEntityname());
		docManagement.setEntitygeneratedid(docManagementRequest.getEntitygeneratedid());
		docManagement.setUploadedby(docManagementRequest.getUploadedby());
		docManagement.setUploadedon(docManagementRequest.getUploadedon());
		docManagement.setRemarks(docManagementRequest.getRemarks());
		docManagentRepository.save(docManagement);
		return "Document Uploaded  Successfully";
	}

	public String deleteDocDetail(Long id) {
		docManagentRepository.deleteById(id);
		return "Document  Deleted Successfully";
	}

	public List<DocManagementResponse> getdocDetails() {

		return docManagentRepository.findAll().stream()
				.map(docManagement -> new DocManagementResponse(docManagement.getId(), docManagement.getDocname(),
						docManagement.getDocpath(), docManagement.getDoctype(), docManagement.getEntityname(),
						docManagement.getEntitygeneratedid(), docManagement.getUploadedby(),
						docManagement.getUploadedon(), docManagement.getRemarks()))
				.toList();
	}

	private final String FOLDER_PATH = "C:\\Users\\kpds0\\Desktop\\ServerPath\\";

	public DocManagementRequest uploadDoc(MultipartFile multipartFile) throws Exception {
		String docpath = FOLDER_PATH + multipartFile.getOriginalFilename();
		multipartFile.transferTo(new File(docpath));
		DocManagementRequest docManagementRequest = new DocManagementRequest();
		docManagementRequest.setDocname(multipartFile.getOriginalFilename());
		docManagementRequest.setDocpath(docpath);
		docManagementRequest.setDoctype(multipartFile.getName());
		docManagementRequest.setUploadedby("pravu");
		return docManagementRequest;
	}

	
	  public byte[] downloadDoc(String name) throws Exception 
	  {
		  Optional<DocManagement>  docData = docManagentRepository.findByName(name);
		  String filePath = docData.get().getDocpath();		 
		  return Files.readAllBytes(new File(filePath).toPath());
	  }
	 

}
