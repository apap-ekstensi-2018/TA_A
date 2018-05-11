package com.siasisten.service;

import java.util.List;

import com.siasisten.model.PengajuanModel;


public interface PengajuanService {
	PengajuanModel selectPengajuanById(int id);
	PengajuanModel selectPengajuanByIdLowongan(int idLowongan);
	void deletePengajuan(int idlowongan);
	void updatePengajuan (PengajuanModel pengajuan);
	int countPengajuan();
	List<PengajuanModel> selectAllPengajuan ();
}
