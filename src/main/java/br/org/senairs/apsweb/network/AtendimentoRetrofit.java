package br.org.senairs.apsweb.network;

import java.util.ArrayList;
import java.util.List;

import br.org.senairs.apsweb.entidades.Atendimento;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtendimentoRetrofit {
	
	private static AtendimentoRetrofit atendimentoRetrofit;

	private AtendimentoRetrofit() {
	}

	public static AtendimentoRetrofit getInstance() {
		if (atendimentoRetrofit == null)
			atendimentoRetrofit = new AtendimentoRetrofit();
		return atendimentoRetrofit;
	}
	
	public List<Atendimento> listar(){
		final List<Atendimento> listaAtendimentos = new ArrayList<Atendimento>();
		
		Call<List<Atendimento>> call = new RetrofitConfig().getAtendimentoAPIService().getAtendimento();
		try {
			Response<List<Atendimento>> response = call.execute();
			for (Atendimento atendimento : response.body()) {
				listaAtendimentos.add(atendimento);
			}
			System.out.println("retrofit lista: " + listaAtendimentos.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("fora retrofit lista: " + listaAtendimentos.size());

		return listaAtendimentos;

	}
	
	public void cadastrar(Atendimento atendimento) {

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
	
	public boolean deletar(String id) {
		String deletar = "";
		
		Call<ResponseBody> call = new RetrofitConfig().getAtendimentoAPIService().deleteAtendimento(Long.parseLong(id));
		try {
			Response<ResponseBody> response = call.execute();
			deletar = response.body().string();		
			System.out.println("api retornou: "+deletar);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if (!"ok".equalsIgnoreCase(deletar)){
			System.out.println("Deu ruim, nao deletou");
			return false;
		}
		
		return true;
	}	
	
	
}
