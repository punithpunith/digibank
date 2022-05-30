package com.digibank.exc.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digibank.exc.service.MainService;

@RestController
public class TradeController {
	
	@Autowired MainService mainService;
	@Autowired Logger logger;
	
	@PostMapping(value = "/fileUpload")
	public ResponseEntity<String> uploadAttendee(@RequestParam("file") MultipartFile file) {

	    String message1 ="You successfully uploaded " + file.getOriginalFilename() + "!";
	    String message2 ="file not uploaded " + file.getOriginalFilename() + "!";
        String message3 = "FAIL to upload " + file.getOriginalFilename() + "!";
        logger.info("name:{}",file.getOriginalFilename() );
	    try {
	    	return mainService.store(file)?ResponseEntity.status(HttpStatus.OK).body(message1):ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message2);

	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message3);
	    }
	}
	@PostMapping(value = "/getSummaryReport")
	public ResponseEntity<String> getReport() {return null;}
	}
