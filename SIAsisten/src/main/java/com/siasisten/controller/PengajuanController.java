package com.siasisten.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siasisten.model.LowonganModel;
import com.siasisten.model.MahasiswaModel;
import com.siasisten.model.MatkulModel;
import com.siasisten.model.PengajuanModel;
import com.siasisten.service.LowonganService;
import com.siasisten.service.MahasiswaService;
import com.siasisten.service.MatkulService;
import com.siasisten.service.PengajuanService;

@Controller
public class PengajuanController {
	
	@Autowired
	private MahasiswaService mahasiswaService;
	
	@Autowired
	private PengajuanService pengajuanService;
	
	@Autowired
	private LowonganService lowonganService;
	
	@Autowired
	private MatkulService matkulService;
	
	@RequestMapping("/pengajuan/view/{id}")
    public String lihatPengajuan(Model model, @PathVariable(value = "id") int id)
    {
		PengajuanModel pengajuan = pengajuanService.selectPengajuanById(id);
		if(pengajuan != null) {
			LowonganModel lowongan = lowonganService.selectLowonganbyID(pengajuan.getIdLowongan());
			MatkulModel matkul = matkulService.selectMatkulbyId(lowongan.getIdMatkul());	
			MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswabyNPM(pengajuan.getUsernameMhs());
			
		    model.addAttribute("pengajuan", pengajuan);
		   	model.addAttribute("matkul", matkul);
		   	model.addAttribute("mahasiswa", mahasiswa);
		   	System.out.println(pengajuan.getIsAccepted());
		    return "view-pengajuan";
		}
		else {
			model.addAttribute("idlowongan", id);
			return "not-found";
		}
    }
	
	@PostMapping("/pengajuan/hapus/{id_lowongan}")
	public String hapusPengajuan(@PathVariable(value = "id_lowongan") int id_lowongan) 
	{
		pengajuanService.deletePengajuan(id_lowongan);
		return "success-delete-pengajuan";
	}
	
}
