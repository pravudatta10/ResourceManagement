package com.nichebit.resourcemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nichebit.resourcemanagement.dto.DocManagementRequest;
import com.nichebit.resourcemanagement.dto.DocManagementResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.service.DocManagementService;

@RestController
@RequestMapping("/document")
public class DocManagementController {

	@Autowired
	DocManagementService docManagementService;

	@PostMapping("/add")
	public DocManagementResponse addDocument(@RequestBody DocManagementRequest docManagementRequest) {
		return docManagementService.saveDocDetail(docManagementRequest);

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateDocument(@RequestBody DocManagementRequest docManagementRequest) {
		return docManagementService.updateDocDetail(docManagementRequest);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteDocument(@PathVariable Long id) {
		return docManagementService.deleteDocDetail(id);
	}

	@GetMapping("/all")
	public List<DocManagementResponse> getDocument() {
		return docManagementService.getdocDetails();
	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadDoc(@RequestParam("file") MultipartFile multipartFile) {
		if (multipartFile == null || multipartFile .isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Select one File");
		}

		try {
			DocManagementRequest uploadDoc = docManagementService.uploadDoc(multipartFile);
			return ResponseEntity.status(HttpStatus.OK).body(uploadDoc);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File Upload Failed");
		}
	}

	@GetMapping("/download/{filename}")
	public ResponseEntity<byte[]> downloadDoc(@PathVariable String filename) {
		try {
			byte[] docdata = docManagementService.downloadDoc(filename);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(docdata);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
