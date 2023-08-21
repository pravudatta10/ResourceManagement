package com.nichebit.resourcemanagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.nichebit.resourcemanagement.dto.HolidayMasterRequest;
import com.nichebit.resourcemanagement.dto.HolidayMasterResponse;
import com.nichebit.resourcemanagement.dto.ReturnResponse;
import com.nichebit.resourcemanagement.entity.HolidayMaster;
import com.nichebit.resourcemanagement.repository.HolidayMasterRepository;

@Service
public class HolidayMasterService {

	@Autowired
	HolidayMasterRepository holidayMasterRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;
	
	ReturnResponse returnResponse = new ReturnResponse();
	
	public ResponseEntity<?> saveHolidayMaster(HolidayMasterRequest holidayMasterRequest){
		HolidayMaster holidayMaster = this.modelMapper.map(holidayMasterRequest, HolidayMaster.class);
		holidayMasterRepository.save(holidayMaster);
		returnResponse.setStatus("HolidayMaster Saved successfully.");
		return ResponseEntity.ok(returnResponse);	
	}
	
	public ResponseEntity<?> updateHolidayMaster(HolidayMasterRequest holidayMasterRequest) {
		HolidayMaster holidayMaster = holidayMasterRepository.findById(holidayMasterRequest.getId()).orElse(null);
		if (holidayMaster == null) {
			returnResponse.setStatus("HolidayMaster not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			holidayMaster = this.modelMapper.map(holidayMasterRequest, HolidayMaster.class);
			holidayMasterRepository.save(holidayMaster);
			returnResponse.setStatus("HolidayMaster updated successfully.");
			return ResponseEntity.ok(returnResponse);
		}

	}
	
	public ResponseEntity<?> deleteHolidayMaster(Long id) {
		HolidayMaster holidayMaster = holidayMasterRepository.findById(id).orElse(null);
		if (holidayMaster == null) {
			returnResponse.setStatus("HolidayMaster  not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnResponse);
		} else {
			holidayMasterRepository.deleteById(id);
			returnResponse.setStatus("HolidayMaster deleted successfully.");
			return ResponseEntity.ok(returnResponse);
		}

	}
	
	
	 public HolidayMasterService(HolidayMasterRepository holidayMasterRepository) {
	        this.holidayMasterRepository = holidayMasterRepository;
	    }
	
	public List<HolidayMasterResponse> getHolidayMaster() {
		return holidayMasterRepository.findAll().stream()
				.map(holidayMaster -> new HolidayMasterResponse(holidayMaster.getId(), holidayMaster.getClient(), holidayMaster.getH_date(),
						holidayMaster.getH_type(), holidayMaster.getRemarks()))
				.toList();
	}
	
	
}
