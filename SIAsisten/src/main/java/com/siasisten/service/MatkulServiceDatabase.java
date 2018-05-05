package com.siasisten.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.siasisten.dao.MatkulDAO;
import com.siasisten.model.MatkulModel;

public class MatkulServiceDatabase implements MatkulService{
	@Autowired
	private MatkulDAO matkulDao;
	
	@Override
	public MatkulModel selectMatkulbyId(int id) {
		// TODO Auto-generated method stub
		return matkulDao.selectMatkulbyId(id);
	}

	@Override
	public List<MatkulModel> selectAllMatkul() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MatkulModel selectMatkulbyKode(String kode) {
		// TODO Auto-generated method stub
		return null;
	}
}
