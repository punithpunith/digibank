package com.digibank.exc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digibank.exc.model.TradeModel;
import com.digibank.exc.repository.TradeRepository;

@Service
public class MainService {
	@Autowired TradeRepository tradeRepository;
	
	public boolean store(MultipartFile file) throws IOException, NumberFormatException, ParseException {
		String line;
		InputStream is = file.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		List<TradeModel> mapp = new ArrayList<TradeModel>();
		boolean b=false;
		while ((line = br.readLine()) != null) {			
			if(b) {
			String[] a = line.split(",");
			TradeModel ar = new TradeModel();
			ar.setOrderNo(a[0]);
			ar.setShareName(a[1]);
			ar.setShareId(a[2]);
			ar.setQuantity(a[3]);
			ar.setBuyOrSell(a[4]);
			ar.setPricePerShare(Integer.parseInt(a[5]));
			ar.setCustId(Integer.parseInt(a[6]));
			ar.setAcct(Integer.parseInt(a[7]));
			ar.setFrontDeskOffId(Integer.parseInt(a[8]));
			ar.setBranchCode(a[9]);
			ar.setTradingCharge(Integer.parseInt(a[10]));
			ar.setExchange(a[11]);
			ar.setTradeDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").parse(a[12]));
		
			mapp.add(ar);
			
			}
			b=true;
		}
		tradeRepository.saveAll(mapp);
		return true;
		}
}
