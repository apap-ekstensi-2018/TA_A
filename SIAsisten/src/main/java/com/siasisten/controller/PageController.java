package com.siasisten.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
}
