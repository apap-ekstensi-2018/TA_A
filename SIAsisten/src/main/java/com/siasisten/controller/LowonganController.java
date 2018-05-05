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

import com.siasisten.dao.MatkulDAO;
import com.siasisten.model.LowonganModel;
import com.siasisten.model.LowonganModelDTO;
import com.siasisten.model.MatkulModel;
import com.siasisten.service.LowonganService;
import com.siasisten.service.MatkulService;

@Controller
public class LowonganController {

	@Autowired
	LowonganService lowonganDAO;
	
	@Autowired
	MatkulDAO matkulDao;
	
	@Autowired
	MatkulService matkulService;

	
	
	@RequestMapping("/lowongan")
    public String index (Model model)
    {
    	model.addAttribute("pageTitle", "Lowongan");
        return "index";
    }
	

	@RequestMapping("/lowongan/tambah")
	public String tambahLowongan(@ModelAttribute ("lowongan") LowonganModel lowongan, Model model)
	{
		List<MatkulModel> matkul = matkulDao.selectAllMatkul();
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

	@RequestMapping("/lowongan/view/{idlowongan}")
    public String cariLowongan (Model model,
            @PathVariable(value = "idlowongan") int idlowongan)
    {
		LowonganModel lm = lowonganDAO.selectLowonganbyID(idlowongan);
		MatkulModel mk = matkulDao.selectMatkulbyId(lm.getIdMatkul());
		int isopen = lm.getIsOpen(); //gw ganti methodnya ke getIsOpen sama tipe data ke int - Fadly
		model.addAttribute("tittle", "Cari Lowongan");
	    	model.addAttribute("lm", lm);
	    	model.addAttribute("isopen",isopen);
	    	model.addAttribute("matkul", mk);
	    return "view";
    }
	
	@RequestMapping("/lowongan/ubah/{id_lowongan}")
	public String lowonganUbah (Model model, @PathVariable(value = "id_lowongan", required = false) int id_lowongan) 
	{
		LowonganModel lowongan = lowonganDAO.selectLowonganbyID(id_lowongan);
		MatkulModel matkul = matkulDao.selectMatkulbyId(lowongan.getIdMatkul());
		int is_open = lowongan.getIsOpen(); //gw ganti methodnya ke getIsOpen sama tipe data ke int - Fadly
		model.addAttribute("matkul", matkul);
		model.addAttribute("is_open", is_open);
		model.addAttribute("lowongan", lowongan);
		return "ubah-lowongan";
	}
	
	@PostMapping("/lowongan/ubah/submit")
	public String ubahSubmit (Model model, @RequestParam(value = "matakuliah", required = false) String matakuliah,
	   @RequestParam(value = "status", required = false) int statusFixed,
	   @RequestParam(value = "jml_slot", required = false) int jml_slot,
	   @RequestParam(value = "idlowongan", required = false) int idlowongan,
	   @RequestParam(value = "id_matkul", required = false) int id_matkul) 
	{
		LowonganModel lowongan = new LowonganModel(idlowongan, id_matkul, statusFixed, jml_slot);
		lowonganDAO.updateLowongan(lowongan);
		model.addAttribute("message", "Lowongan dengan id " + idlowongan + " , mata kuliah " + matakuliah + " berhasil diubah");
		return "success-update";
	}
	
	@RequestMapping("/lowongan/viewall")
    public String cariSemuaLowongan (Model model)
    {
		List<LowonganModelDTO> allLowonganDTO = new ArrayList<>();
		List<LowonganModel> allLowongan = lowonganDAO.selectAllLowongan();
		model.addAttribute("allLowongan", allLowongan);
		for(LowonganModel lMod : allLowongan) {
			LowonganModelDTO lDto = new LowonganModelDTO();
			lDto.setId(lMod.getId());
			MatkulModel mMod = matkulService.selectMatkulbyId(lMod.getIdMatkul());
			lDto.setNamaMatkul(mMod.getNamaMatkul());
			lDto.setIsOpen(lMod.getIsOpen());
			lDto.setJmlLowongan(lMod.getJmlLowongan());
			allLowonganDTO.add(lDto);
		}
		model.addAttribute("allLowonganDTO", allLowonganDTO);
	    return "viewall";
    }
}
