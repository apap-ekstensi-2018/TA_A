package com.siasisten.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.siasisten.model.LowonganModel;
import com.siasisten.model.MatkulModel;
import com.siasisten.service.LowonganService;
import com.siasisten.service.MatkulService;

@Controller
public class SIAsistenController {
	
	@Autowired
	LowonganService lowonganDAO;
	@Autowired
	MatkulService matkulDao;
	
	@RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("pageTitle", "Home");
        return "index";
    }

	@RequestMapping("/lowongan/tambah")
	public String tambahLowongan(@ModelAttribute ("lowongan") LowonganModel lowongan, Model model)
	{
		List <MatkulModel> matkul = matkulDao.selectAllMatkul();
		model.addAttribute("matkul", matkul);
		if(lowongan.getIdMatkul()==0) {
			return "form-addLowongan";
		}
		else {
			model.addAttribute("idMatkul", lowongan.getIdMatkul());
			model.addAttribute("message", "Lowongan dengan Mata Kuliah" + lowongan.getIdMatkul() + "berhasil ditambahkan");
			lowonganDAO.addLowongan(lowongan);
			return "berhasil-tambah";
		}
	
	}
}
