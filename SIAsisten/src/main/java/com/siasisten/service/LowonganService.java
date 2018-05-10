package com.siasisten.service;

import java.util.List;

import com.siasisten.model.LowonganModel;

public interface LowonganService {
	LowonganModel selectLowongan (String id);

	LowonganModel selectLowonganbyID (int idlowongan);
	
    List<LowonganModel> selectAllLowongan ();
    
    List<LowonganModel> selectAllLowonganByDosen (String listIdMatkul);

    void addLowongan (LowonganModel student);

    void deleteLowongan (int idlowongan);
    
    void updateLowongan (LowonganModel student);
    int countLowongan ();
    
    int cekLowongan(int id_matkul);
}
