package com.siasisten.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siasisten.service.LowonganService;
import com.siasisten.service.PengajuanService;

@Controller
public class PageController {
	@Autowired
	LowonganService lowonganDAO;
	@Autowired
	PengajuanService pengajuanDAO;
	
	@RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("pageTitle", "Home");
        return "login";
    }
	
	@RequestMapping("/login")
	public String login (Model model) {
		model.addAttribute("pageTitle", "Home");
		return "login";
	}
	
	@RequestMapping("/home")
	public String home (Model model) {
		
		int jumlahLowongan = lowonganDAO.countLowongan();
		int jumlahPengajuan = pengajuanDAO.countPengajuan();
		model.addAttribute("totalLowongan", jumlahLowongan);
		model.addAttribute("totalPengajuan", jumlahPengajuan);
		model.addAttribute("pageTitle", "Home");
		return "home";
	}
}
