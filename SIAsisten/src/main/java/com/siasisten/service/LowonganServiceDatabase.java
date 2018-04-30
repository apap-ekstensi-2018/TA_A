package com.siasisten.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten.dao.LowonganMapper;
import com.siasisten.model.LowonganModel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class LowonganServiceDatabase implements LowonganService{
	@Autowired
	private LowonganMapper LowonganMapper;
	
	@Override
	public LowonganModel selectLowongan(String id) {
		log.info ("Select Data Lowongan with Id {}", id);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LowonganModel> selectAllLowongan() {
		log.info ("Select All Data Lowongan");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLowongan(LowonganModel student) {
		log.info ("Tambah Data Lowongan");
		LowonganMapper.addLowongan(student);
	}

	@Override
	public void deleteLowongan(LowonganModel student) {
		log.info ("Delete Data Lowongan");
		// TODO Auto-generated method stub
	}

	@Override
	public void updateLowongan(LowonganModel student) {
		log.info ("Update Data Lowongan");
		// TODO Auto-generated method stub
	}
}
