package com.nichebit.resourcemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.nichebit.resourcemanagement.entity.DocManagement;
import com.nichebit.resourcemanagement.service.DocManagementService;

@RestController
@RequestMapping("/document")
public class DocManagementController {
	
	DocManagementRequest docManagementRequest=new DocManagementRequest();

	@Autowired
	DocManagementService docManagementService;

	@PostMapping("/add")
	public DocManagementResponse addDocument(@RequestBody DocManagementRequest docManagementRequest) {
		return docManagementService.saveDocDetail(docManagementRequest);

	}

	@PutMapping("/update")
	public ResponseEntity<DocManagement> updateDocument(@RequestBody DocManagementRequest docManagementRequest) {
		return docManagementService.updateDocDetail(docManagementRequest);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ReturnResponse> deleteDocument(@PathVariable Long id) {
		return docManagementService.deleteDocDetail(id);
	}

	@GetMapping("/all")
	public List<DocManagementResponse> getDocument() {
		return docManagementService.getdocDetails();
	}

	@PostMapping("/upload")
	public ResponseEntity<DocManagementRequest> uploadDoc(@RequestParam("file") MultipartFile multipartFile) {
		if (multipartFile == null || multipartFile .isEmpty()) {
			docManagementRequest.setStatus("Please Select one File");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(docManagementRequest);
		}

		try {
			DocManagementRequest uploadDoc = docManagementService.uploadDoc(multipartFile);
			return ResponseEntity.status(HttpStatus.OK).body(uploadDoc);
		} catch (Exception e) {
			e.printStackTrace();
			docManagementRequest.setStatus("File Upload Failed");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(docManagementRequest);
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
	@GetMapping("/downloadexcel")
	public ResponseEntity<byte[]> downloadExcel(@RequestParam("name") String name,@RequestParam("financialyear") int financialyear,@RequestParam("month") String month) {
		try {
			byte[] docdata = docManagementService.downloadexcel(name, financialyear, month);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(docdata);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/docbyid")
	public List<DocManagementResponse> getDocDetaisById(@RequestParam("entityname")  String entityname,@RequestParam("entitygeneratedid") String entitygeneratedid)
	{
		return docManagementService.getDocDetailsByid(entityname,entitygeneratedid);
	}

}
