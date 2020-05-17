package br.org.senairs.apsweb.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9091/api-pet/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public AtendimentoAPIService getAtendimentoAPIService() {
        return this.retrofit.create(AtendimentoAPIService.class);
    }
}
