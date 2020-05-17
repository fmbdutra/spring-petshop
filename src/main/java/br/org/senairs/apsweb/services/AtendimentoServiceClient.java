package br.org.senairs.apsweb.services;

import java.util.ArrayList;
import java.util.List;

import br.org.senairs.apsweb.entidades.Atendimento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtendimentoServiceClient {
	public static void chamada() {

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
}
