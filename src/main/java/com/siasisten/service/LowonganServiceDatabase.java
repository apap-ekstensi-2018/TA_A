package com.siasisten.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten.dao.LowonganMapper;
import com.siasisten.model.LowonganModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LowonganServiceDatabase implements LowonganService {

	@Autowired 
	private LowonganMapper lowonganMapper;

	@Override
	public LowonganModel selectLowonganById(int id_lowongan) {
		// TODO Auto-generated method stub
		return lowonganMapper.selectLowonganById(id_lowongan);
	}

	@Override
	public List<LowonganModel> selectAllLowongan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLowongan(LowonganModel student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLowongan(LowonganModel student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLowongan(int idlowongan, int id_matkul, boolean statusFixed, int jml_slot) {
		// TODO Auto-generated method stub
		lowonganMapper.updateLowongan(idlowongan, id_matkul, statusFixed, jml_slot);
	}
	

}
