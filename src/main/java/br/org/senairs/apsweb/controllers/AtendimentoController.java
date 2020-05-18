package br.org.senairs.apsweb.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

		atendimento.setEntregaStatus("N");

		LocalDateTime data = LocalDateTime.now();
		atendimento.setDataEntrega(data.plusHours(5).toString());

		AtendimentoService.cadastrar(atendimento);

		return "cadastrado";
	}

	@RequestMapping(value = "/testeAdiciona", method = RequestMethod.POST)
	public String adicionarAtendimento() {
		Atendimento a = new Atendimento();
		
		LocalDateTime agora = LocalDateTime.now();
		agora = agora.plusHours(5);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
		String agoraFormatado = agora.format(formatter);
		System.out.println("LocalDateTime formatado: " + agoraFormatado);

		a.setAnimal_nome("teste");
		a.setAnimal_raca("cachorro");
		a.setEntregaStatus("N");
		a.setPessoa_nome("senhor teste");
		a.setTipoAtendimento("banho");

		a.setDataEntrega(agoraFormatado);

		AtendimentoService.cadastrar(a);

		return "cadastrado";
	}

	@GetMapping("/listar")
	public String listaAtendimento() {

		AtendimentoService.listar();

		return "listarAtendimentos";
	}

}
