package com.siasisten.dao;

import java.util.List;

import com.siasisten.model.MatkulModel;

public interface MatkulDAO {
	MatkulModel selectMatkulbyId (String id);
	List<MatkulModel> selectAllMatkul ();
	MatkulModel selectMatkulbyKode (String kode);
}
