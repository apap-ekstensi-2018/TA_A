package com.siasisten.service;

import java.util.List;

import com.siasisten.model.PengajuanModel;

public interface PengajuanService {
	PengajuanModel selectPengajuanById(int id);
	PengajuanModel selectPengajuanByIdLowongan(int idLowongan);
	void deletePengajuan(int idlowongan);
	void updatePengajuan (PengajuanModel pengajuan);
	int countPengajuan();
	int countPengajuanById(int id);
	int countDiterimaById(int id);
	int isRegister (String username, int idLowongan);
	List<PengajuanModel> selectAllPengajuan();
	List<PengajuanModel> selectAllPengajuanMhs(String usernameMhs);
	List<PengajuanModel> selectAllPengajuanDosen(String listLowongan);
	String cekPengajuan(String username_mahasiswa, int id_lowongan);
	void addPengajuan(PengajuanModel pengajuan);
	List<String> selectPengajuanByIdMatkul(int idMatkul);
	List<String> selectAllMatkulAsistenByUsername(String username_mahasiswa);
}
