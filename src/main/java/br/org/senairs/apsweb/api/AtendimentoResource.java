package br.org.senairs.apsweb.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.senairs.apsweb.entidades.Atendimento;

@RestController
@RequestMapping(value = "/api-pet")
public class AtendimentoResource {

	@Autowired
	AtendimentoRepository atendimentoRepository;

	@GetMapping("/atendimentos")
	public List<Atendimento> listaAtendimentos() {
		System.out.println("API - Chegou atendimentos");
		return atendimentoRepository.findAll();
	}

	@GetMapping("/atendimentos/{id}")
	public Atendimento listaAtendimentoUnico(@PathVariable(value = "id") long id) {
		System.out.println("API - Busca por id " + id);
		return atendimentoRepository.findById(id);
	}

	@PostMapping("/atendimentos")
	public Atendimento salvaAtendimento(@RequestBody @Valid Atendimento atendimento) {
		System.out.println("API - salvando atendimento");
		return atendimentoRepository.save(atendimento);
	}

	@DeleteMapping("/atendimentos/{id}")
	public String deletaAtendimento(@PathVariable(value = "id") long id) {
		System.out.println("API - delete por id");

		String retorno = "";
		if (atendimentoRepository.findById(id) != null) {
			atendimentoRepository.deleteById(id);
			
			retorno = "ok"; 
		}else {
			retorno = "erro";
		}		
		
		return retorno ;
	}
}
