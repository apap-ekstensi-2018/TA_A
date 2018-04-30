package com.siasisten.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siasisten.model.MatkulModel;
@Service
public interface MatkulDAO {
	MatkulModel selectMatkulbyId (int id);
	List<MatkulModel> selectAllMatkul ();
	MatkulModel selectMatkulbyKode (String kode);
}
