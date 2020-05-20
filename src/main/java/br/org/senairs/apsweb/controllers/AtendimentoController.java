package br.org.senairs.apsweb.controllers;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.org.senairs.apsweb.entidades.Atendimento;
import br.org.senairs.apsweb.services.AtendimentoService;

@Controller
@RequestMapping("/atendimentos")
public class AtendimentoController {

	AtendimentoService atendimentoService = AtendimentoService.getInstance();

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
		Atendimento novoAtendimento = atendimentoService.cadastrar(atendimento);
		if (novoAtendimento != null) {
			model.addAttribute("cadastro_status", "Cadastrou!");
			model.addAttribute("data_entrega", "Data de entrega: " + novoAtendimento.getDataEntrega());
		} else {
			model.addAttribute("cadastro_status", "Cadastro invalido!");
		}

		return "cadastrado";
	}

	@GetMapping({ "/listar", "", "/" })
	public String listaAtendimento(Model model) {

		model.addAttribute("listaAtendimento", atendimentoService.listar());

		return "listarAtendimentos";
	}

	@RequestMapping(value = "/deleta", method = RequestMethod.GET)
	public String deletaAtendimento(@RequestParam(value = "id") String id) {
		String model = "";

		if (atendimentoService.deletar(id)) {
			model = "redirect:/atendimentos/listar";
		} else {
			model = "error";
		}

		return model;
	}

	@GetMapping(value = "/testeAdiciona")
	public String adicionarAtendimento() {
		Atendimento a = new Atendimento();
		a.setAnimal_nome("teste");
		a.setAnimal_raca("gato");
		a.setPessoa_nome("senhor teste");
		a.setTipoAtendimento("banho");
		atendimentoService.cadastrar(a);

		Atendimento b = new Atendimento();
		b.setAnimal_nome("teste");
		b.setAnimal_raca("gato");
		b.setPessoa_nome("senhor teste");
		b.setTipoAtendimento("tosa");
		atendimentoService.cadastrar(b);

		Atendimento c = new Atendimento();
		c.setAnimal_nome("teste");
		c.setAnimal_raca("cachorro");
		c.setPessoa_nome("senhor teste");
		c.setTipoAtendimento("veterinario");
		atendimentoService.cadastrar(c);

		return "redirect:/atendimentos/listar";
	}

}
