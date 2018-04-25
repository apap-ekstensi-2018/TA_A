package com.siasisten.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SIAsistenController {
	@RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("pageTitle", "Home");
        return "index";
    }
}
