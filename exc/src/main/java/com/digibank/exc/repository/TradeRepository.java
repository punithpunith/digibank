package com.digibank.exc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digibank.exc.model.TradeModel;


@Repository
public interface TradeRepository extends CrudRepository<TradeModel, Integer> {
	@Query(value =  "select * from TRADE_MODEL",nativeQuery = true)
	public List<TradeModel> getAllRecs();
	
	@Query(value =  "select * from TRADE_MODEL t where t.FRONT_DESK_OFF_ID=? and t.TRADE_DATE_TIME=?",nativeQuery = true)
	public List<TradeModel> findByFrontTaskId(String frontDeskOfficerId,String date);
}
