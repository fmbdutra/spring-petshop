package br.org.senairs.apsweb.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.senairs.apsweb.entidades.Atendimento;

@RestController
@RequestMapping(value="/api-pet")
public class AtendimentoResource {
	
	@Autowired
	AtendimentoRepository atendimentoRepository;
	
	@GetMapping("/atendimentos")
	public List<Atendimento> listaAtendimentos(){
		return atendimentoRepository.findAll();
	}	
	
	@GetMapping("/atendimentos/{id}")
	public Atendimento listaAtendimentoUnico(@PathVariable(value="id") long id){
		return atendimentoRepository.findById(id);
	}
	
	@PostMapping("/atendimentos")
	public Atendimento salvaAtendimento(@RequestBody @Valid Atendimento atendimento) {
		return atendimentoRepository.save(atendimento);
	}
	
	@DeleteMapping("/atendimentos")
	public void deletaAtendimento(@RequestBody @Valid Atendimento atendimento) {
		atendimentoRepository.delete(atendimento);
	}
	
	@PutMapping("/atendimentos")
	public Atendimento entregaAtendimento(@RequestBody @Valid Atendimento atendimento) {
		LocalDate time = LocalDate.now(); 		
		atendimento.setEntregaStatus("S");
		atendimento.setDataEntrega(time.toString());
		
		return atendimentoRepository.save(atendimento);
	}	
	
}
