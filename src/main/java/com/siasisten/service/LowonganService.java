package com.siasisten.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siasisten.model.LowonganModel;

@Service
public interface LowonganService {
	LowonganModel selectLowongan (String id);

    List<LowonganModel> selectAllLowongan ();

    void addLowongan (LowonganModel student);


    void deleteLowongan (LowonganModel student);
    
    
    void updateLowongan (LowonganModel student);
}
