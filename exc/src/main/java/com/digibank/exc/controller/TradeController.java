package com.digibank.exc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.digibank.exc.model.ErrorModel;
import com.digibank.exc.model.TradeModel;
import com.digibank.exc.service.MainService;

@RestController
public class TradeController {

	@Autowired
	MainService mainService;
	@Autowired
	Logger logger;

	@PostMapping(value = "/fileUpload")
	public ResponseEntity<String> uploadAttendee(@RequestParam("file") MultipartFile file) {

		String message1 = "You successfully uploaded " + file.getOriginalFilename() + "!";
		String message2 = "file not uploaded " + file.getOriginalFilename() + "!";
		String message3 = "FAIL to upload " + file.getOriginalFilename() + "!";
		logger.info("name:{}", file.getOriginalFilename());
		try {
			return mainService.store(file) ? ResponseEntity.status(HttpStatus.OK).body(message1)
					: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message2);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message3);
		}
	}

	@PostMapping(value = "/addSingleTrade")
	public ResponseEntity<TradeModel> getTrade(@RequestBody TradeModel tradeModel) {
		 TradeModel tradeMod = mainService.addTrade(tradeModel);
		if (tradeMod!=null) 
			return ResponseEntity.status(HttpStatus.OK).body(tradeMod);
		else {
			ErrorModel er = new ErrorModel();
			er.setHttpStatus(HttpStatus.OK.toString());
			er.setMessage("Exception occured while inserting");
			er.setRequest(tradeModel.toString());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}
