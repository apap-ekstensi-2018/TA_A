package com.siasisten.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten.model.PengajuanModel;
import com.siasisten.dao.PengajuanMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PengajuanServiceDatabase implements PengajuanService{
	@Autowired
	private PengajuanMapper PengajuanMapper; 
	
	public PengajuanModel selectPengajuanbyID(int id) {
		// TODO Auto-generated method stub
		log.info ("select Lowongan with id {}", id);
		return PengajuanMapper.selectPengajuanbyID(id);
	}
}
