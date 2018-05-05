package com.siasisten.service;

import java.util.List;

import com.siasisten.model.MatkulModel;

public interface MatkulService {
	
	MatkulModel selectMatkulbyId (int id);
	List<MatkulModel> selectAllMatkul ();
	MatkulModel selectMatkulbyKode (String kode);
}
