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
		log.info ("select pengajuan with id pengajuan {}", id);
		return pengajuanMapper.selectPengajuanById(id);
	}

	@Override
	public void deletePengajuan(int idPengajuan) {
		log.info ("Delete pengajuan with id pengajuan {}", idPengajuan);
		pengajuanMapper.deletePengajuan(idPengajuan);
	}

	@Override
	public PengajuanModel selectPengajuanByIdLowongan(int idLowongan) {
		log.info ("select pengajuan with id lowongan {}", idLowongan);
		return pengajuanMapper.selectPengajuanByIdLowongan(idLowongan);
	}

	@Override
	public void updatePengajuan(PengajuanModel pengajuan) {
		log.info ("update data pengajuan with id {}", pengajuan.getId());
		pengajuanMapper.updatePengajuan(pengajuan);
	}

	@Override
	public int countPengajuan() {
		log.info ("Hitung Jumlah Pengajuan");
		return pengajuanMapper.countPengajuan();
	}

	@Override
	public List<PengajuanModel> selectAllPengajuan() {
		log.info ("Select Seluruh Data Pengajuan");
		return pengajuanMapper.selectAllPengajuan();
	}

	@Override
	public List<PengajuanModel> selectAllPengajuanMhs(String usernameMhs) {
		log.info ("Select Seluruh Data Pengajuan Mahasiswa with NPM {}", usernameMhs);
		return pengajuanMapper.selectAllPengajuanMhs(usernameMhs);
	}

	@Override
	public int countPengajuanById(int id) {
		log.info ("Hitung Data Pengajuan with id {}", id);
		return pengajuanMapper.countPengajuanById(id);
	}

	@Override
	public int countDiterimaById(int id) {
		log.info ("Hitung Data Pengajuan yang Diterima with id {}", id);
		return pengajuanMapper.countDiterimaById(id);
	}

	@Override
	public List<PengajuanModel> selectAllPengajuanDosen(String listIdLowongan) {
		log.info ("Select Data Pengajuan Dosen with list id lowongan ({})", listIdLowongan);
		return pengajuanMapper.selectAllPengajuanDosen(listIdLowongan);
	}

	@Override
	public int isRegister(String username, int idLowongan) {
		log.info ("Cek data mahasiswa pelamar");
		return pengajuanMapper.isRegister(username, idLowongan);
	}

	public String cekPengajuan(String username_mahasiswa, int id_lowongan) {
		log.info ("Cek data pengajuan");
		return pengajuanMapper.cekPengajuan(username_mahasiswa, id_lowongan);
	}

	@Override
	public void addPengajuan(PengajuanModel pengajuan) {
		log.info ("Insert Data Pengajuan");
		pengajuanMapper.addPengajuan(pengajuan);
	}

	@Override
	public List<String> selectPengajuanByIdMatkul(int idMatkul) {
		log.info ("Select Data Pengajuan with id matkul ({})", idMatkul);
		return pengajuanMapper.selectAllAsistenByIdLowongan(idMatkul);
	}

	@Override
	public List<String> selectAllMatkulAsistenByUsername(String username_mahasiswa) {
		log.info ("Select All Matkul Asisten with username ({})", username_mahasiswa);
		return pengajuanMapper.selectAllMatkulAsistenByUsername(username_mahasiswa);
	}
}
