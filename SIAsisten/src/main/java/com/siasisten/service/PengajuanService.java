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
	List<PengajuanModel> selectAllPengajuan();
	List<PengajuanModel> selectAllPengajuanMhs(String usernameMhs);
}
