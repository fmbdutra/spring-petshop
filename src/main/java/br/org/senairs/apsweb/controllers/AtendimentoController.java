package br.org.senairs.apsweb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.org.senairs.apsweb.entidades.Atendimento;
import br.org.senairs.apsweb.services.AtendimentoServiceClient;

@Controller
@RequestMapping("/atendimentos")
public class AtendimentoController {
	
	@GetMapping({"/cadastro"})
	public ModelAndView cadastro(Map<String, Object> model) {

		List<String> animaisRaca = new ArrayList<>();
		animaisRaca.add("Gato");
		animaisRaca.add("Cachorro");
		model.put("animaisRaca", animaisRaca);

		return new ModelAndView("formAtendimento", "atendimento", new Atendimento());
	}
//	
//	@RequestMapping(value="/adicionarAtendimento", method = RequestMethod.POST)
//	public String adicionarAtendimento(Atendimento atendimento, HttpSession session, Model model) {
//		
//		
//		
//		return "";
//	}
	
	@GetMapping("/listar")
	public String listaAtendimento() {
		
		
		AtendimentoServiceClient.chamada();
		
		return "listarAtendimentos";
	}

}
