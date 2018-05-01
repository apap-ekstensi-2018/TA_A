package com.siasisten.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siasisten.dao.MahasiswaMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService {
	 @Autowired
	 private MahasiswaMapper mahasiswaMapper;
	 
	 @Override
	 public boolean isAsdos (String npm)
	 {
        log.info ("select student with npm {}", npm);
        return mahasiswaMapper.isAsdos (npm);
	 }
}
