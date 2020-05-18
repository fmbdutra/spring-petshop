package br.org.senairs.apsweb.services;

import java.util.ArrayList;
import java.util.List;

import br.org.senairs.apsweb.entidades.Atendimento;
import br.org.senairs.apsweb.network.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtendimentoService {
	public static void listar() {

		System.out.println("oi");

		List<Atendimento> listaAtendimentos = new ArrayList<Atendimento>();

		Call<List<Atendimento>> call = new RetrofitConfig().getAtendimentoAPIService().getAtendimento();
		call.enqueue(new Callback<List<Atendimento>>() {
			@Override
			public void onResponse(Call<List<Atendimento>> call, Response<List<Atendimento>> response) {
				if (response.body() != null) {
					for (Atendimento s : response.body()) {
						listaAtendimentos.add(s);
					}

					System.out.println(listaAtendimentos.size());
				}
			}

			@Override
			public void onFailure(Call<List<Atendimento>> call, Throwable t) {
				System.out.println(t.getMessage());

			}
		});
	}

	public static void cadastrar(Atendimento atendimento) {
		
		Call<Atendimento> call = new RetrofitConfig().getAtendimentoAPIService().cadastrarAtendimento(atendimento);
		call.enqueue(new Callback<Atendimento>() {
			@Override
			public void onResponse(Call<Atendimento> call, Response<Atendimento> response) {

			}

			@Override
			public void onFailure(Call<Atendimento> call, Throwable t) {
				System.out.println("Erro ao fazer requisicao! Erro:" + t.getMessage());
			}			
		});
		

		
	}
	
	
	
}
