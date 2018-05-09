package com.siasisten.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten.dao.PengajuanMapper;
import com.siasisten.model.PengajuanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PengajuanServiceDatabase implements PengajuanService{

	@Autowired
	private PengajuanMapper pengajuanMapper;
	
	@Override
	public PengajuanModel selectPengajuanById(int id) {
		// TODO Auto-generated method stub
		return pengajuanMapper.selectPengajuanById(id);
	}

	@Override
	public void deletePengajuan(int idlowongan) {
		// TODO Auto-generated method stub
		pengajuanMapper.deletePengajuan(idlowongan);
	}

	@Override
	public PengajuanModel selectPengajuanByIdLowongan(int idLowongan) {
		// TODO Auto-generated method stub
		return pengajuanMapper.selectPengajuanByIdLowongan(idLowongan);
	} 
}
