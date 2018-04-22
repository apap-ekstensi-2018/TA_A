package com.siasisten.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siasisten.dao.MahasiswaDAO;
import com.siasisten.model.MahasiswaModel;
import com.siasisten.service.MahasiswaService;

@RestController
@RequestMapping("/asisten-dosen")
public class SIAsistenRestController {
	@Autowired
	MahasiswaService mahasiswaService;
	
	@Autowired
    MahasiswaDAO mahasiswaDAO;
	
	@RequestMapping(value = "/cek-status", method = RequestMethod.GET)
	public boolean isAsdos (@RequestParam(value = "id", required = false) String id) {
		// Get Mahasiswa Data from Mahasiswa API
		MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyId(Integer.parseInt(id)); 
		String npm = mahasiswa.getNpm();
		boolean result = mahasiswaService.isAsdos(npm);
		return result;
	}
}
