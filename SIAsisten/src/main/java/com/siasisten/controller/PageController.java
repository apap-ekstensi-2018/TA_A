package com.siasisten.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("pageTitle", "Home");
        return "index";
    }
	
	@RequestMapping("/login")
	public String login (Model model) {
		model.addAttribute("pageTitle", "Login");
		return "login";
	}
}
