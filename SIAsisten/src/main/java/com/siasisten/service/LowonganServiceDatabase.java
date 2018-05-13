package com.siasisten.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.siasisten.dao.LowonganMapper;
import com.siasisten.model.LowonganModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LowonganServiceDatabase implements LowonganService{
	@Autowired
	private LowonganMapper LowonganMapper;
	
	@Override
	public LowonganModel selectLowongan(String id) {
		log.info ("Select Data Lowongan with Id {}", id);
		return null;
	}

	@Override
	public List<LowonganModel> selectAllLowongan() {
		log.info ("Select All Data Lowongan");
		return LowonganMapper.selectAllLowongan();
	}

	@Override
	public void addLowongan(LowonganModel student) {
		log.info ("Tambah Data Lowongan");
		LowonganMapper.addLowongan(student);
	}

	@Override
	public void updateLowongan(LowonganModel student) {
		log.info ("Update Data Lowongan");
		LowonganMapper.updateLowongan(student);
	}

	@Override
	public void deleteLowongan(int idlowongan) {
		log.info ("Delete Data Lowongan with Id {}", idlowongan);
		LowonganMapper.deleteLowongan(idlowongan);
	}
	
	@Override
    public LowonganModel selectLowonganbyID (int idlowongan)
    {
        log.info ("select Lowongan with id {}", idlowongan);
        return LowonganMapper.selectLowonganbyID(idlowongan);
    }

	@Override
	public int cekLowongan(int id_matkul) {
		log.info ("Check Data Lowongan with Id {}", id_matkul);
		return LowonganMapper.cekLowongan(id_matkul);
	}

	@Override
	public int countLowongan() {
		log.info ("Hitung Jumlah Lowongan");
		return LowonganMapper.countLowongan();
	}

	@Override
	public List<LowonganModel> selectAllLowonganByDosen(String listIdMatkul) {
		log.info ("Select All Data Lowongan by Dosen");
		return LowonganMapper.selectAllLowonganByDosen(listIdMatkul);
	}
}
