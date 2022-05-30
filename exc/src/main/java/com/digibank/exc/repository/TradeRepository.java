package com.digibank.exc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digibank.exc.model.TradeModel;


@Repository
public interface TradeRepository extends CrudRepository<TradeModel, Integer> {}
