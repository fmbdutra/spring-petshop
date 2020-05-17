package br.org.senairs.apsweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InicioController {
	
	@GetMapping({"/", "/petshop", ""})
	public String hello(Model model) {		
		return "inicio";
	}
}
