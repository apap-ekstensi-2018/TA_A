package com.siasisten.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.siasisten.dao.RuanganMatkulMapper;
import com.siasisten.model.RuanganMatkulModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RuanganMatkulServiceDatabase implements RuanganMatkulService {
	@Autowired
	private RuanganMatkulMapper ruanganMatkulMapper;
	 
	@Override
	public List<RuanganMatkulModel> selectRuanganbyIdMatkul(int idMatkul) {
		log.info ("Select Ruangan Matkul with IdMatkul {}", idMatkul);
		return ruanganMatkulMapper.selectRuanganbyIdMatkul(idMatkul);
	}

}
