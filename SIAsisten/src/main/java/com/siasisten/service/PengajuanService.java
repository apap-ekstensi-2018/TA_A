package com.siasisten.service;

import com.siasisten.model.LowonganModel;
import com.siasisten.model.PengajuanModel;


public interface PengajuanService {
	PengajuanModel selectPengajuanById(int id);
	PengajuanModel selectPengajuanByIdLowongan(int idLowongan);
	void deletePengajuan(int idlowongan);
	void updatePengajuan (PengajuanModel pengajuan);
	int countPengajuan();
	String cekPengajuan(String username_mahasiswa);
	void addPengajuan(PengajuanModel pengajuan);
}
