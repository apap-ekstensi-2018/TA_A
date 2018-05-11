package com.siasisten.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siasisten.dao.DosenDAO;
import com.siasisten.dao.MatkulDAO;
import com.siasisten.model.DosenModel;
import com.siasisten.model.LowonganModel;
import com.siasisten.model.LowonganModelDTO;
import com.siasisten.model.MatkulModel;
import com.siasisten.model.RuanganMatkulModel;
import com.siasisten.service.LowonganService;
import com.siasisten.service.RuanganMatkulService;


@Controller
public class LowonganController {

	@Autowired
	LowonganService lowonganDAO;
	
	@Autowired
	MatkulDAO matkulDao;
	
	@Autowired
	RuanganMatkulService ruanganMatkulDAO;
	
	@Autowired
	DosenDAO dosenDAO;
	
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
		model.addAttribute("pageTitle", "Tambah Lowongan");
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

		List<RuanganMatkulModel> rm = ruanganMatkulDAO.selectRuanganbyIdMatkul(mk.getId());
		

		model.addAttribute("tittle", "Cari Lowongan");
	    	model.addAttribute("lm", lm);
	    	model.addAttribute("isopen",isopen);
	    	model.addAttribute("matkul", mk);
	    	model.addAttribute("ruangan", rm);
	    	System.out.println("id ruangan"+rm);
	    	model.addAttribute("pageTitle", "View Lowongan");
	    return "view";
    }
	
	@RequestMapping("/lowongan/ubah/{id_lowongan}")
	public String lowonganUbah (Model model, @PathVariable(value = "id_lowongan", required = false) int id_lowongan) 
	{
		LowonganModel lowongan = lowonganDAO.selectLowonganbyID(id_lowongan);
		MatkulModel matkul = matkulDao.selectMatkulbyId(lowongan.getIdMatkul());
		model.addAttribute("matkul", matkul);
		model.addAttribute("lowongan", lowongan);
		model.addAttribute("pageTitle", "Ubah Lowongan");
		model.addAttribute("id_lowongan", id_lowongan);
		return "ubah-lowongan";
	}
	
	@PostMapping("/lowongan/ubah/submit")
	public String ubahSubmit (Model model, @RequestParam(value = "matakuliah", required = false) String matakuliah,
										   @RequestParam(value = "status", required = false) int statusFixed,
										   @RequestParam(value = "jml_slot", required = false) int jml_slot,
										   @RequestParam(value = "id_lowongan", required = false) int idLowongan,
										   @RequestParam(value = "id_matkul", required = false) int id_matkul) 
	{
		LowonganModel lowongan = new LowonganModel(idLowongan, id_matkul, statusFixed, jml_slot);
		lowonganDAO.updateLowongan(lowongan);
		model.addAttribute("message", "Lowongan dengan id " + idLowongan + " , mata kuliah " + matakuliah + " berhasil diubah");
		return "success-update";
	}
	
	@RequestMapping("/lowongan/hapus/{id_lowongan}")
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
	
	@RequestMapping("/lowongan/viewall")
    public String cariSemuaLowongan (Model model)
    {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userId = auth.getName();
			String listIdMatkul = "";
			Collection<? extends GrantedAuthority> authorities
		     = auth.getAuthorities();
			List<String> roles = new ArrayList<String>();
			for (GrantedAuthority a : authorities) {
		        roles.add(a.getAuthority());
		    }
			
			List<LowonganModelDTO> allLowonganDTO = new ArrayList<>();
			
			if (roles.contains("dosen"))
			{
				DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
				List<MatkulModel> matkulDosen = dosen.getMataKuliahList();
				listIdMatkul = matkulDosen.stream()
						.map(p -> String.valueOf(p.getId()))
						.collect(Collectors.joining(","));
				
				List<LowonganModel> allLowongan = lowonganDAO.selectAllLowonganByDosen(listIdMatkul);
				
				model.addAttribute("allLowongan", allLowongan);
				for(LowonganModel lMod : allLowongan) {
					LowonganModelDTO lDto = new LowonganModelDTO();
					lDto.setId(lMod.getId());
					MatkulModel mMod = matkulDao.selectMatkulbyId(lMod.getIdMatkul());
					lDto.setNamaMatkul(mMod.getNamaMatkul());
					lDto.setIsOpen(lMod.getIsOpen());
					lDto.setJmlLowongan(lMod.getJmlLowongan());
					allLowonganDTO.add(lDto);
				}
			}
			else
			{
				List<LowonganModel> allLowongan = lowonganDAO.selectAllLowongan();
				
				model.addAttribute("allLowongan", allLowongan);
				for(LowonganModel lMod : allLowongan) {
					LowonganModelDTO lDto = new LowonganModelDTO();
					lDto.setId(lMod.getId());
					MatkulModel mMod = matkulDao.selectMatkulbyId(lMod.getIdMatkul());
					lDto.setNamaMatkul(mMod.getNamaMatkul());
					lDto.setIsOpen(lMod.getIsOpen());
					lDto.setJmlLowongan(lMod.getJmlLowongan());
					allLowonganDTO.add(lDto);
				}
			}
			
			model.addAttribute("listIdMatkul", listIdMatkul);
			model.addAttribute("allLowonganDTO", allLowonganDTO);
			model.addAttribute("pageTitle", "View All Lowongan");
		    return "viewall";
    }
}
