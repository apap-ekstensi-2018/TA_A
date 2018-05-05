package com.siasisten.controller;


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
import com.siasisten.model.MatkulModel;
import com.siasisten.service.LowonganService;

@Controller
public class LowonganController {

	@Autowired
	LowonganService lowonganDAO;
	
	@Autowired
	MatkulDAO matkulDao;

	private int idLowongan;
	private int id_matkul;
	
	@RequestMapping("/lowongan")
    public String index (Model model)
    {
    	model.addAttribute("pageTitle", "Lowongan");
        return "index";
    }
	

	@RequestMapping("/lowongan/tambah")
	public String tambahLowongan(Model model, @ModelAttribute("lowongan") LowonganModel lowongan)
	{
		List<MatkulModel> matkul = matkulDao.selectAllMatkul();
		model.addAttribute("matkul", matkul);
		if(lowongan.getIdMatkul()==0) {
			return "form-addLowongan";
		}
		else {
			if (isExistLowongan(lowongan.getIdMatkul())> 0) {
				return "failed-add";
			}
			else {
				model.addAttribute("idMatkul", lowongan.getIdMatkul());
				model.addAttribute("message", "Lowongan dengan Mata Kuliah" + lowongan.getIdMatkul() + "berhasil ditambahkan");
				lowonganDAO.addLowongan(lowongan);
				return "success-add";
			}
		}
	}
	
	public int isExistLowongan(int idMatkul) {
		return lowonganDAO.cekLowongan(idMatkul);
	}
	
	@RequestMapping("/lowongan/view/{idlowongan}")
    public String cariLowongan (Model model,
            @PathVariable(value = "idlowongan") int idlowongan)
    {
		LowonganModel lm = lowonganDAO.selectLowonganbyID(idlowongan);
		MatkulModel mk = matkulDao.selectMatkulbyId(lm.getIdMatkul());
		int isopen = lm.getIsOpen();
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
		int is_open = lowongan.getIsOpen();
		model.addAttribute("matkul", matkul);
		model.addAttribute("is_open", is_open);
		model.addAttribute("lowongan", lowongan);
		idLowongan = id_lowongan;
	    id_matkul = matkul.getId();
		return "ubah-lowongan";
	}
	
	@PostMapping("/lowongan/ubah/submit")
	public String ubahSubmit (Model model, @RequestParam(value = "matakuliah", required = false) String matakuliah,
										   @RequestParam(value = "status", required = false) int statusFixed,
										   @RequestParam(value = "jml_slot", required = false) int jml_slot) 
	{
		LowonganModel lowongan = new LowonganModel(idLowongan, id_matkul, statusFixed, jml_slot);
		lowonganDAO.updateLowongan(lowongan);
		model.addAttribute("message", "Lowongan dengan id " + idLowongan + " , mata kuliah " + matakuliah + " berhasil diubah");
		return "success-update";
	}
	
	@RequestMapping("/lowongan/delete/{id_lowongan}")
    public String deleteLowongan (Model model, @PathVariable(value = "id_lowongan", required = false) int idlowongan)
    {
		LowonganModel lowongan = lowonganDAO.selectLowonganbyID(idlowongan);
        if (lowongan != null) {
        	lowonganDAO.deleteLowongan(idlowongan);
        	model.addAttribute("message", "Lowongan dengan id " + lowongan.getId() + " berhasil dihapus");
            return "success-delete";
        } else {
            model.addAttribute ("idlowongan", idlowongan);
            return "not-found";
        }
    }
}
