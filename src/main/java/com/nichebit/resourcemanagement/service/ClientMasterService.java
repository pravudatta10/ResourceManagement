package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nichebit.resourcemanagement.dto.ClientMasterRequest;
import com.nichebit.resourcemanagement.dto.ClientMasterResponse;

import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.entity.ClientMaster;
import com.nichebit.resourcemanagement.entity.Employee;
import com.nichebit.resourcemanagement.repository.ClientMasterRepository;

public class ClientMasterService {
    @Autowired
	ClientMasterRepository clientMasterRepository;
    @Autowired
	private ModelMapper modelMapper;
    
    ReturnResponse returnResponse = new ReturnResponse();
    
    public ResponseEntity<?> saveClientMaster(ClientMasterRequest clientMasterRequest) {
		
    	      ClientMaster clientMaster = this.modelMapper.map(clientMasterRequest, ClientMaster.class);
    	      clientMasterRepository.save(clientMaster);
    	      returnResponse.setStatus("client Saved successfully.");
    	     return ResponseEntity.ok(returnResponse);
    
}
    
    public ResponseEntity<?> updateClientMaster(ClientMasterRequest clientMasterRequest) {
    	 ClientMaster clientMaster = clientMasterRepository.findById(clientMasterRequest.getId()).orElse(null);
		if (clientMaster == null) {
			returnResponse.setStatus("client not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			clientMaster = this.modelMapper.map(clientMasterRequest, ClientMaster.class);
			clientMasterRepository.save(clientMaster);
			returnResponse.setStatus("client updated successfully.");
			return ResponseEntity.ok(returnResponse);
		}
    }
  
    public ResponseEntity<?> deleteClientMaster(Long id){
    	ClientMaster clientMaster = clientMasterRepository.findById(id).orElse(null);
    	if(clientMaster==null) {
    		returnResponse.setStatus("Client  not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			clientMasterRepository.deleteById(id);
			returnResponse.setStatus("Client deleted successfully.");
			return ResponseEntity.ok(returnResponse);
		}

    }
    public List<ClientMasterResponse> getClient() {
		return clientMasterRepository.findAll().stream()
				.map(clientMaster -> new ClientMasterResponse(clientMaster.getId(), clientMaster.getClient(), clientMaster.getWorkinghours()))
				.toList();
}
}
