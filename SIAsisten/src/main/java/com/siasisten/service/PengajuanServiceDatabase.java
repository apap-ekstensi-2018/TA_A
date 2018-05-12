package com.siasisten.service;

import java.util.List;

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
	public List<PengajuanModel> selectAllPengajuan() {
		return pengajuanMapper.selectAllPengajuan();
	}

	@Override
	public List<PengajuanModel> selectAllPengajuanMhs(String usernameMhs) {
		// TODO Auto-generated method stub
		return pengajuanMapper.selectAllPengajuanMhs(usernameMhs);
	}

	@Override
	public int countPengajuanById(int id) {
		// TODO Auto-generated method stub
		return pengajuanMapper.countPengajuanById(id);
	}

	@Override
	public int countDiterimaById(int id) {
		// TODO Auto-generated method stub
		return pengajuanMapper.countDiterimaById(id);
	}
}
