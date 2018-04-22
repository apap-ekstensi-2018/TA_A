package com.siasisten.dao;

import java.util.List;

import com.siasisten.model.DosenModel;

public interface DosenDAO {
	DosenModel selectDosenbyId (int id);
	List<DosenModel> selectAllDosen ();
	DosenModel selectDosenbyNIP (String nip);
}
