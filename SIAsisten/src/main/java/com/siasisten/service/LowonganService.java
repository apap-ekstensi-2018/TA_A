package com.siasisten.service;

import java.util.List;

import com.siasisten.model.LowonganModel;

public interface LowonganService {
	LowonganModel selectLowongan (String id);

	LowonganModel selectLowonganbyID (int idlowongan);
	
    List<LowonganModel> selectAllLowongan ();


    void addLowongan (LowonganModel student);


    void deleteLowongan (LowonganModel student);
    
    
    void updateLowongan (LowonganModel student);
}
