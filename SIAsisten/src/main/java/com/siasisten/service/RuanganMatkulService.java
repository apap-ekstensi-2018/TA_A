package com.siasisten.service;

import java.util.List;


import com.siasisten.model.RuanganMatkulModel;

public interface RuanganMatkulService {
	
	List<RuanganMatkulModel>  selectRuanganbyIdMatkul (int idMatkul);

}
