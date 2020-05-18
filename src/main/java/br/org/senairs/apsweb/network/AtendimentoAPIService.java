package br.org.senairs.apsweb.network;

import java.util.List;

import br.org.senairs.apsweb.entidades.Atendimento;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AtendimentoAPIService {
	 @GET("atendimentos")
	    Call<List<Atendimento>> getAtendimento();
	 
	 @POST("atendimentos")
	    Call<Atendimento> cadastrarAtendimento(@Body Atendimento novoAtendimento);
}
