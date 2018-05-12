package com.siasisten.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siasisten.dao.DosenDAO;
import com.siasisten.dao.MahasiswaDAO;
import com.siasisten.model.DosenModel;
import com.siasisten.model.LowonganModelDTO;
import com.siasisten.model.MahasiswaModel;
import com.siasisten.service.LowonganService;
import com.siasisten.service.PengajuanService;

@Controller
public class PageController {
	@Autowired
	LowonganService lowonganDAO;
	@Autowired
	PengajuanService pengajuanDAO;
	@Autowired
	DosenDAO dosenDAO;
	@Autowired
	MahasiswaDAO mahasiswaDAO;
	
	@RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("pageTitle", "Home");
        return "login";
    }
	
	@RequestMapping("/login")
	public String login (Model model, Authentication auth) {
		
		
		model.addAttribute("pageTitle", "Home");
		return "login";
	}
	
	@RequestMapping("/home")
	public String home (Model model, Authentication auth) {
		
		Collection<? extends GrantedAuthority> authorities
	     = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
	        roles.add(a.getAuthority());
	    }
		
		
		String roleUser = roles.get(0);
		System.out.println(roleUser);
		System.out.println(roleUser.substring(5, 10));
		model.addAttribute("role",roleUser.substring(5, roleUser.length()));
		
		String userId = auth.getName();
		if (roles.contains("ROLE_dosen"))
		{
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			model.addAttribute("namaUser", dosen.getNama());
		}else {
			MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(userId);
			model.addAttribute("namaUser", mahasiswa.getNama());
		}
		
		int jumlahLowongan = lowonganDAO.countLowongan();
		int jumlahPengajuan = pengajuanDAO.countPengajuan();
		model.addAttribute("totalLowongan", jumlahLowongan);
		model.addAttribute("totalPengajuan", jumlahPengajuan);
		model.addAttribute("pageTitle", "Home");
		return "home";
	}
}
