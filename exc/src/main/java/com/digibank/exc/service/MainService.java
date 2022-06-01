package com.digibank.exc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digibank.exc.model.TradeModel;
import com.digibank.exc.repository.TradeRepository;

@Service
public class MainService {
	@Autowired
	TradeRepository tradeRepository;

	public boolean store(MultipartFile file) throws IOException, NumberFormatException, ParseException {
		String line;
		InputStream is = file.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		List<TradeModel> mapp = new ArrayList<TradeModel>();
		boolean firstRow = false;
		while ((line = br.readLine()) != null) {
			if (firstRow) {
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
			firstRow = true;
		}
		tradeRepository.saveAll(mapp);
		return true;
	}
	public List<TradeModel> getAllRecs(){
		return tradeRepository.getAllRecs();
	}
	public TradeModel addTrade(TradeModel tradeModel) {
		TradeModel ret = tradeRepository.save(tradeModel);
		return ret;
	}

	public Map<String, Object> findByFrontTaskId(String frontDeskOfficerId, String date) {
		List<TradeModel> traderecords = tradeRepository.findByFrontTaskId(frontDeskOfficerId,date);
		
		System.out.println(traderecords);
		System.out.println(date);
		Map<String, Object> response = Map.of("report", getReportData(traderecords, date), "trade_records",
				traderecords);
		System.out.println(response);
		return response;

	}

	private Map<String, Object> getReportData(List<TradeModel> repData, String date) {
		Map<String, Object> report = new HashMap<String, Object>();
		report.put("tot_num_trade", repData.size());
		report.put("diff_clients", repData.stream().map(a -> a.getShareName()).collect(Collectors.toSet()).size());
		report.put("buy_count",
				repData.stream().filter(a -> a.getBuyOrSell().equals("B")).collect(Collectors.toList()).size());
		report.put("sell_count",
				repData.stream().filter(a -> a.getBuyOrSell().equals("S")).collect(Collectors.toList()).size());
		report.put("dates", date);
		return report;

	}
}
