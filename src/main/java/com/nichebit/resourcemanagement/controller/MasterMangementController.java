package com.nichebit.resourcemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nichebit.resourcemanagement.dto.MasterMangementRequest;
import com.nichebit.resourcemanagement.dto.MasterMangementResponse;
import com.nichebit.resourcemanagement.dto.MasterMangementResponseForType;
import com.nichebit.resourcemanagement.service.MasterMangementService;

@RestController
@RequestMapping("/masterdata")
public class MasterMangementController {

	@Autowired
	MasterMangementService masterMangementService;

	@PostMapping("/add")
	public MasterMangementResponse addMasterData(@RequestBody MasterMangementRequest masterMangementRequest) {
		return masterMangementService.addMasterdata(masterMangementRequest);

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateMasterData(@RequestBody MasterMangementRequest masterMangementRequest) {
		return masterMangementService.updateMasterdata(masterMangementRequest);

	}

	@DeleteMapping("/delete/{id}")
	public String deleteMasterData(@PathVariable Long id) {
		return masterMangementService.deleteMasterData(id);
	}

	@GetMapping("/all")
	public List<MasterMangementResponse> allMasterData() {
		return masterMangementService.getallMasterData();
	}

	@GetMapping("/getbytype/{type}")
	public List<MasterMangementResponse> getDistinctypes(@PathVariable String type) {
		return masterMangementService.getbytype(type);
	}

	@GetMapping("/gettypes")
	public List<MasterMangementResponseForType> getalltypes() {
		return masterMangementService.getDistinctTypes();

	}
}
