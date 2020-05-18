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
	
	AtendimentoRetrofit atendimentoRetrofit = AtendimentoRetrofit.getInstance();

	public List<Atendimento> listar() {
		
		return atendimentoRetrofit.listar();

//		final List<Atendimento> listaAtendimentos = new ArrayList<Atendimento>();
//
//		Call<List<Atendimento>> call = new RetrofitConfig().getAtendimentoAPIService().getAtendimento();
//		try {
//			Response<List<Atendimento>> response = call.execute();
//			for (Atendimento atendimento : response.body()) {
//				listaAtendimentos.add(atendimento);
//			}
//			System.out.println("retrofit lista: " + listaAtendimentos.size());
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		System.out.println("fora retrofit lista: " + listaAtendimentos.size());
//
//		return listaAtendimentos;
	}

	public Atendimento cadastrar(Atendimento atendimento) {

		atendimento.setEntregaStatus("N");

		boolean tipoAtendimento = "banho".equalsIgnoreCase(atendimento.getTipoAtendimento())
				|| "tosa".equalsIgnoreCase(atendimento.getTipoAtendimento());

		if (tipoAtendimento) {
			atendimento.setDataEntrega(defineDataParaEntrega());
		} else {
			atendimento.setDataEntrega("Aguardar chamada");
		}
		
		atendimentoRetrofit.cadastrar(atendimento);
		
		return atendimento;

//		Call<Atendimento> call = new RetrofitConfig().getAtendimentoAPIService().cadastrarAtendimento(atendimento);
//		call.enqueue(new Callback<Atendimento>() {
//			@Override
//			public void onResponse(Call<Atendimento> call, Response<Atendimento> response) {
//
//			}
//
//			@Override
//			public void onFailure(Call<Atendimento> call, Throwable t) {
//				System.out.println("Erro ao fazer requisicao! Erro:" + t.getMessage());
//			}
//		});

	}

	public String deletar(String id) {
//		String deletar = "";
//		
//		Call<ResponseBody> call = new RetrofitConfig().getAtendimentoAPIService().deleteAtendimento(Long.parseLong(id));
//		try {
//			Response<ResponseBody> response = call.execute();
//			deletar = response.body().string();		
//			System.out.println("api retornou: "+deletar);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		
//		if (!"ok".equalsIgnoreCase(deletar)){
//			System.out.println("Deu ruim, nao deletou");
//			return false;
//		}
//		
//		return true;	
		
		String retorno = "";
		boolean retornoResponse = atendimentoRetrofit.deletar(id);
		
		if(retornoResponse) {
			retorno = "deletou";
		}else {
			retorno = "nao deletou";
		}
		
		return retorno;
	}	
	

	private String defineDataParaEntrega() {

		LocalDateTime agora = LocalDateTime.now();
		agora = agora.plusHours(5);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
		String agoraFormatado = agora.format(formatter);

		return agoraFormatado;
	}
}
