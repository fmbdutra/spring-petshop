package br.org.senairs.apsweb.services;

import java.util.List;

import br.org.senairs.apsweb.entidades.Atendimento;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AtendimentoAPIService {
	 @GET("atendimentos")
	    public Call<List<Atendimento>> getAtendimento();
}
