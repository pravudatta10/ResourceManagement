package com.nichebit.resourcemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nichebit.resourcemanagement.dto.DocManagementRequest;
import com.nichebit.resourcemanagement.dto.DocManagementResponse;
import com.nichebit.resourcemanagement.service.DocManagementService;

@RestController
@CrossOrigin("http://localhost:4200")
public class DocManagementController {

	@Autowired
	DocManagementService docManagementService;
	@PostMapping("/adddocument")
	public String addDocument(@RequestBody DocManagementRequest docManagementRequest) {
		return docManagementService.saveDocDetail(docManagementRequest);

	}

	@PutMapping("/updatedocument")
	public String updateDocument(@RequestBody DocManagementRequest docManagementRequest) {
		return docManagementService.updateDocDetail(docManagementRequest);
	}

	@DeleteMapping("/deletedocument/{id}")
	public String deleteDocument(@PathVariable Long id) {
		return docManagementService.deleteDocDetail(id);
	}

	@GetMapping("/getalldoc")
	public List<DocManagementResponse> getDocument() {
		return docManagementService.getdocDetails();
	}

}
