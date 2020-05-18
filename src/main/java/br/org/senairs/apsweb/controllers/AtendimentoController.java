package br.org.senairs.apsweb.controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.org.senairs.apsweb.entidades.Atendimento;
import br.org.senairs.apsweb.services.AtendimentoService;

@Controller
@RequestMapping("/atendimentos")
public class AtendimentoController {

	@GetMapping({ "/cadastro" })
	public ModelAndView cadastro(Model model) {
		ModelAndView modelAndView = new ModelAndView("formAtendimento", "atendimento", new Atendimento());

		String[] tipoAtendimento = { "Banho", "Tosa", "Veterinario" };
		model.addAttribute("tipoAtendimento", tipoAtendimento);

		String[] animalRaca = { "Gato", "Cachorro" };
		model.addAttribute("animalRaca", animalRaca);

		return modelAndView;
	}

	@RequestMapping(value = "/adicionarAtendimento", method = RequestMethod.POST)
	public String adicionarAtendimento(Atendimento atendimento, HttpSession session, Model model) {
		
//		atendimento.setId(0);
		atendimento.setEntregaStatus("N");
		atendimento.setDataEntrega("02-02-2020");

		AtendimentoService.cadastrar(atendimento);

		return "cadastrado";
	}

	@RequestMapping(value = "/testeAdiciona", method = RequestMethod.POST)
	public String adicionarAtendimento() {
		Atendimento a = new Atendimento();
		LocalDateTime t = LocalDateTime.now();
		a.setAnimal_nome("teste");
		a.setAnimal_raca("cachorro");
		a.setDataEntrega(t.toString());
		a.setEntregaStatus("N");
		a.setPessoa_nome("senhor teste");
		a.setTipoAtendimento("banho");

		a.setId(0);

		AtendimentoService.cadastrar(a);

		return "cadastrado";
	}

	@GetMapping("/listar")
	public String listaAtendimento() {

		AtendimentoService.listar();

		return "listarAtendimentos";
	}

}
