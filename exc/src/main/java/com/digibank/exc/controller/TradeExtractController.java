package com.digibank.exc.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digibank.exc.model.TradeModel;
import com.digibank.exc.service.MainService;

@RestController
public class TradeExtractController {
	@Autowired
	MainService mainService;
	@Autowired
	Logger logger;


	@GetMapping(value = "/getByFrontDeskId")
	public ResponseEntity<Map<String, Object>> uploadAttendee(@RequestParam String frontDeskId,@RequestParam String date) {
		System.out.println(frontDeskId+date);//should use as param value format 2022-05-11 10:34:20.653	
		Map<String, Object> listT = mainService.findByFrontTaskId(frontDeskId,date);//@DateTimeFormat(pattern="MMddyyyy") 
		return ResponseEntity.status(HttpStatus.OK).body(listT);
		
	}
	@GetMapping(value = "/allRecs")
	public ResponseEntity<List<TradeModel>> getRecs() {
		List<TradeModel> listT = mainService.getAllRecs(); 
		
		return ResponseEntity.status(HttpStatus.OK).body(listT);
		
	}
}
