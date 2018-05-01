package com.siasisten.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.siasisten.model.LowonganModel;

public interface LowonganService {
	
	LowonganModel selectLowonganById (int id_lowongan);

    List<LowonganModel> selectAllLowongan ();

    void addLowongan (LowonganModel student);

    void deleteLowongan (LowonganModel student);
    
    void updateLowongan (int idlowongan, int id_matkul, boolean statusFixed, int jml_slot);
}
