package br.org.senairs.apsweb.network;

import java.util.List;

import br.org.senairs.apsweb.entidades.Atendimento;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AtendimentoAPIService {
	 @GET("atendimentos")
	    Call<List<Atendimento>> getAtendimento();
	 
	 @POST("atendimentos")
	    Call<Atendimento> cadastrarAtendimento(@Body Atendimento novoAtendimento);
	 
	 @DELETE("atendimentos/{id}")
	 	Call<ResponseBody> deleteAtendimento(@Path("id") Long id);
}
