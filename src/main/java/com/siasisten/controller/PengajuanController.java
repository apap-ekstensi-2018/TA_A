package com.siasisten.controller;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siasisten.dao.MahasiswaDAO;
import com.siasisten.dao.MatkulDAO;
import com.siasisten.model.LowonganModel;
import com.siasisten.model.LowonganModelDTO;
import com.siasisten.model.MahasiswaModel;
import com.siasisten.model.MatkulModel;
import com.siasisten.model.PengajuanModel;
import com.siasisten.model.RuanganMatkulModel;
import com.siasisten.service.LowonganService;
import com.siasisten.service.MahasiswaService;
import com.siasisten.service.MatkulService;
import com.siasisten.service.PengajuanService;
import com.siasisten.service.RuanganMatkulService;


@Controller
public class PengajuanController {

	@Autowired
	PengajuanService pengajuanDAO;
	
	@Autowired
	LowonganService lowonganDAO;
	
	@Autowired
	MatkulDAO matkulDAO;
	
	@Autowired
	MahasiswaDAO mahasiswaDAO;
	
	@Autowired
	MatkulService matkulService;
	
	@RequestMapping("/pengajuan/view/{id}")
    public String lihatPengajuan (Model model,
            @PathVariable(value = "id") int id)
    {
		PengajuanModel lm = pengajuanDAO.selectPengajuanbyID(id);		
		LowonganModel mk = lowonganDAO.selectLowonganbyID(lm.getIdlowongan());
		MatkulModel mm = matkulDAO.selectMatkulbyId(mk.getIdMatkul());	
		MahasiswaModel rm = mahasiswaDAO.selectMahasiswabyNPM(lm.getUsernameMhs());
		
//		boolean isaccepted = lm.isAccepted();
		model.addAttribute("tittle", "Cari Lowongan");
	    	model.addAttribute("lm", lm);
	    	model.addAttribute("matkul",mm);
	    	model.addAttribute("user",rm);
//	    	model.addAttribute("isaccepted", isaccepted);
	    	model.addAttribute("pageTitle", "View Pengajuan");
	    return "view-pengajuan";
    }
}
