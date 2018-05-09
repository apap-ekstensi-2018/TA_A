package com.siasisten.service;

import org.apache.ibatis.annotations.Param;

import com.siasisten.model.PengajuanModel;


public interface PengajuanService {
	PengajuanModel selectPengajuanById(int id);
	PengajuanModel selectPengajuanByIdLowongan(int idLowongan);
	void deletePengajuan(int idlowongan); 
}
