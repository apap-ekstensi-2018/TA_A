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
		log.info ("select student with npm {}", id);
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

	@Override
	public void updatePengajuan(PengajuanModel pengajuan) {
		// TODO Auto-generated method stub
		pengajuanMapper.updatePengajuan(pengajuan);
	}

	@Override
	public int countPengajuan() {
		// TODO Auto-generated method stub
		return pengajuanMapper.countPengajuan();
	}

	@Override
	public String cekPengajuan(String username_mahasiswa) {
		// TODO Auto-generated method stub
		return pengajuanMapper.cekPengajuan(username_mahasiswa);
	}

	@Override
	public void addPengajuan(PengajuanModel pengajuan) {
		// TODO Auto-generated method stub
		pengajuanMapper.addPengajuan(pengajuan);
	}

	
}
