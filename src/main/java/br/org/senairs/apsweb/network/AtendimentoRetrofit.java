package br.org.senairs.apsweb.network;

import java.util.ArrayList;
import java.util.List;

import br.org.senairs.apsweb.entidades.Atendimento;
import okhttp3.ResponseBody;
import retrofit2.Call;
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return listaAtendimentos;
	}
	
	public boolean cadastrar(Atendimento atendimento) {
		
		Call<Atendimento> call = new RetrofitConfig().getAtendimentoAPIService().cadastrarAtendimento(atendimento);
		try {
			Response<Atendimento> response = call.execute();
			
			String IdAtendimentoResponse = String.valueOf(response.body().getId());
			boolean validaId = IdAtendimentoResponse.isEmpty() || "".equalsIgnoreCase(IdAtendimentoResponse);
			if(!(response.isSuccessful() && !validaId)) {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
		return true;
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
