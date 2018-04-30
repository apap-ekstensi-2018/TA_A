package com.siasisten.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten.dao.LowonganMapper;
import com.siasisten.model.LowonganModel;
@Service
public class LowonganServiceDatabase implements LowonganService{
	@Autowired
	private LowonganMapper LowonganMapper;
	@Override
	public LowonganModel selectLowongan(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LowonganModel> selectAllLowongan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLowongan(LowonganModel student) {
		LowonganMapper.addLowongan(student);
		
	}

	@Override
	public void deleteLowongan(LowonganModel student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLowongan(LowonganModel student) {
		// TODO Auto-generated method stub
		
	}
}
