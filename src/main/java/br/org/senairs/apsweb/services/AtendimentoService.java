package br.org.senairs.apsweb.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.org.senairs.apsweb.entidades.Atendimento;
import br.org.senairs.apsweb.network.AtendimentoRetrofit;

public class AtendimentoService {

	private static AtendimentoService atendimentoService;

	private AtendimentoService() {
	}

	public static AtendimentoService getInstance() {
		if (atendimentoService == null)
			atendimentoService = new AtendimentoService();
		return atendimentoService;
	}

	AtendimentoRetrofit atendimentoConexao = AtendimentoRetrofit.getInstance();

	public List<Atendimento> listar() {
		return atendimentoConexao.listar();
	}

	public Atendimento cadastrar(Atendimento atendimento) {
		
		if(validaCadastroDeAtendimento(atendimento)) {
			boolean tipoAtendimento = "banho".equalsIgnoreCase(atendimento.getTipoAtendimento())
					|| "tosa".equalsIgnoreCase(atendimento.getTipoAtendimento());

			if (tipoAtendimento) {
				atendimento.setDataEntrega(defineDataParaEntrega());
			} else {
				atendimento.setDataEntrega("Aguardar ligacao para buscar");
			}

			if(!atendimentoConexao.cadastrar(atendimento)) {
				return atendimento = null;
			}else {
				System.out.println("cadastrou ok");
			}
			
		}else {
			atendimento = null;
		}

		return atendimento;

	}

	public Boolean deletar(String id) {
		boolean retornoResponse = atendimentoConexao.deletar(id);

		return retornoResponse;
	}

	private boolean validaCadastroDeAtendimento(Atendimento atendimento) {
		if (("").equalsIgnoreCase(atendimento.getAnimal_nome()) || atendimento.getAnimal_nome().isEmpty()) {
			return false;
		}

		if (("").equalsIgnoreCase(atendimento.getAnimal_raca()) || atendimento.getAnimal_raca().isEmpty()) {
			return false;
		}

		if (("").equalsIgnoreCase(atendimento.getPessoa_nome()) || atendimento.getPessoa_nome().isEmpty()) {
			return false;
		}
		
		if (("").equalsIgnoreCase(atendimento.getTipoAtendimento()) || atendimento.getTipoAtendimento().isEmpty()) {
			return false;
		}
		if (String.valueOf(atendimento.getId()).contentEquals("") || String.valueOf(atendimento.getId()).isEmpty()) {
			return false;
		}

		return true;
	}

	private String defineDataParaEntrega() {

		LocalDateTime agora = LocalDateTime.now();
		agora = agora.plusHours(5);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String agoraFormatado = agora.format(formatter);

		return agoraFormatado;
	}
}
