package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nichebit.resourcemanagement.dto.DocManagementRequest;
import com.nichebit.resourcemanagement.dto.DocManagementResponse;
import com.nichebit.resourcemanagement.entity.DocManagement;
import com.nichebit.resourcemanagement.repository.DocManagentRepository;

@Service
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

	public List <DocManagementResponse> getdocDetails(){
		
		return docManagentRepository.findAll().stream()
				.map(docManagement ->new DocManagementResponse(docManagement.getId(),docManagement.getDocname(),
						docManagement.getDocpath(),docManagement.getDoctype(),docManagement.getEntityname(),
						docManagement.getEntitygeneratedid(),docManagement.getUploadedby(),
						docManagement.getUploadedon(),docManagement.getRemarks())).toList();
	}
	String FOLDER_PATH="C:\\Users\\kpds0\\Desktop\\ServerPath\\";
	public String uploadDoc(MultipartFile multipartFile) {
		
		String filename=multipartFile.getOriginalFilename();
		//String filePath=FOLDER_PATH+filename;
		//multipartFile.transfer To(new File(filePath));
		return null;
		
	}

}
