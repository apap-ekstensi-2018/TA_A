package com.siasisten.dao;

import java.util.List;

import com.siasisten.model.PegawaiModel;

public interface PegawaiDAO {
	PegawaiModel selectPegawaibyId (String id);
	List<PegawaiModel> selectAllPegawai ();
	PegawaiModel selectPegawaibyNIP (String nip);
}
