package com.siasisten.service;

import com.siasisten.model.MahasiswaModel;

public interface MahasiswaService {
	boolean isAsdos (String npm);
	MahasiswaModel selectMahasiswabyNPM (String npm);
}
