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

import com.nichebit.resourcemanagement.dto.ClientMasterRequest;
import com.nichebit.resourcemanagement.dto.ClientMasterResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.service.ClientMasterService;

@RestController
@RequestMapping("/clientMaster")
public class ClientMasterController {

	@Autowired
	ClientMasterService clientMasterSevice;

	@PostMapping("/add")
	public ResponseEntity<ReturnResponse> addClietMaster(@RequestBody ClientMasterRequest clientMasterRequest)
			throws IllegalArgumentException {
		return clientMasterSevice.saveClientMaster(clientMasterRequest);
	}

	@PutMapping("/update")
	public ResponseEntity<ReturnResponse> updateClietMaster(@RequestBody ClientMasterRequest clientMasterRequest) {
		return clientMasterSevice.updateClientMaster(clientMasterRequest);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ReturnResponse> deleteClietMaster(@PathVariable Long id) {
		return clientMasterSevice.deleteClientMaster(id);
	}

	@GetMapping("/all")
	public List<ClientMasterResponse> getClients() {
		return clientMasterSevice.getClient();
	}
}
