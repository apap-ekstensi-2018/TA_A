package com.siasisten.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten.dao.MahasiswaDAO;
import com.siasisten.dao.MahasiswaMapper;
import com.siasisten.model.MahasiswaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService {
	 @Autowired
	 private MahasiswaMapper mahasiswaMapper;
	 
	 @Autowired
	 private MahasiswaDAO mahasiswaDao;
	 
	 @Override
	 public boolean isAsdos (String npm)
	 {
        log.info ("select student with npm {}", npm);
        return mahasiswaMapper.isAsdos (npm);
	 }

		@Override
		public MahasiswaModel selectMahasiswabyNPM(String npm) {
			// TODO Auto-generated method stub
			return mahasiswaDao.selectMahasiswabyNPM(npm);
		}
}
