package com.siasisten.dao;

import java.util.List;

import com.siasisten.model.MahasiswaModel;

public interface MahasiswaDAO {
	MahasiswaModel selectMahasiswabyId (String id);
	List<MahasiswaModel> selectAllMahasiswa ();
	MahasiswaModel selectMahasiswabyNPM (String npm);
}
