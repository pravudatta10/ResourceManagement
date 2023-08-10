package com.nichebit.resourcemanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nichebit.resourcemanagement.dto.DocManagementRequest;
import com.nichebit.resourcemanagement.dto.DocManagementResponse;
import com.nichebit.resourcemanagement.entity.DocManagement;
import com.nichebit.resourcemanagement.repository.DocManagentRepository;

import lombok.Data;

@Service
@Data
public class DocManagementService {

	@Autowired
	DocManagentRepository docManagentRepository;

	@Autowired
	private ModelMapper modelMapper;

	public DocManagementResponse saveDocDetail(DocManagementRequest docManagementRequest) {
		DocManagement docManagement = this.modelMapper.map(docManagementRequest, DocManagement.class);
		docManagentRepository.save(docManagement);
		return this.modelMapper.map(docManagement, DocManagementResponse.class);
	}

	public ResponseEntity<?> updateDocDetail(DocManagementRequest docManagementRequest) {
		DocManagement docManagement = docManagentRepository.findById(docManagementRequest.getId()).orElse(null);
		if (docManagement == null) {
			return ResponseEntity.notFound().build();
		}
		docManagement = this.modelMapper.map(docManagementRequest, DocManagement.class);
		docManagentRepository.save(docManagement);
		return ResponseEntity.ok(docManagement);
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

	public DocManagementRequest uploadDoc(MultipartFile multipartFile) {
		DocManagementRequest docManagementRequest = new DocManagementRequest();
		DocManagement docManagement = this.modelMapper.map(docManagementRequest, DocManagement.class);
		//String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(0));
		 Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());

		try {
			String docpath = FOLDER_PATH + multipartFile.getOriginalFilename();
			String filename=multipartFile.getOriginalFilename();
			int index = filename.lastIndexOf('.');
			String ext=filename.substring(index + 1);
			multipartFile.transferTo(new File(docpath));
			docManagementRequest.setDocname(filename);
			docManagementRequest.setDocpath(docpath);
			docManagementRequest.setDoctype(ext);
			docManagentRepository.save(docManagement);
			return docManagementRequest;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public byte[] downloadDoc(String name) {
		try {
			Optional<DocManagement> docData = docManagentRepository.findBydocname(name);
			if (docData.isPresent()) {
				String filePath = docData.get().getDocpath();
				return Files.readAllBytes(new File(filePath).toPath());
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
