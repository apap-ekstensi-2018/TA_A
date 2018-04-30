package com.siasisten.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.siasisten.dao.MatkulDAO;
import com.siasisten.model.MatkulModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MatkulServiceRest implements MatkulService {

	@Autowired
	private MatkulDAO matkulDAO;
	
	@Override
	public MatkulModel selectMatkulbyId(int id) {
		// TODO Auto-generated method stub
		return matkulDAO.selectMatkulbyId(id);
	}

	@Override
	public List<MatkulModel> selectAllMatkul() {
		// TODO Auto-generated method stub
		return matkulDAO.selectAllMatkul();
	}

}
