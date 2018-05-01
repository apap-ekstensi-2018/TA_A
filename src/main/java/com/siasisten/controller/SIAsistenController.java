package com.siasisten.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	private int idlowongan;
	private int id_matkul;
	
	@RequestMapping("/lowongan/ubah/{id_lowongan}")
	public String lowonganUbah (Model model, @PathVariable(value = "id_lowongan", required = false) int id_lowongan) 
	{
		LowonganModel lowongan = lowonganDAO.selectLowonganById(id_lowongan);
		MatkulModel matkul = matkulDao.selectMatkulbyId(lowongan.getIdMatkul());
		boolean is_open = lowongan.isOpen();
		model.addAttribute("matkul", matkul);
		model.addAttribute("is_open", is_open);
		model.addAttribute("lowongan", lowongan);
		idlowongan = id_lowongan;
		id_matkul = lowongan.getIdMatkul();
		return "ubah-lowongan";
	}
	
	@PostMapping("/lowongan/ubah/submit")
	public String ubahSubmit (Model model, @RequestParam(value = "matakuliah", required = false) String matakuliah,
										   @RequestParam(value = "status", required = false) boolean statusFixed,
										   @RequestParam(value = "jml_slot", required = false) int jml_slot) 
	{
		lowonganDAO.updateLowongan(idlowongan, id_matkul, statusFixed, jml_slot);
		model.addAttribute("message", "Lowongan dengan id " + idlowongan + " , mata kuliah " + matakuliah + " berhasil diubah");
		return "success-update";
	}
	
}
