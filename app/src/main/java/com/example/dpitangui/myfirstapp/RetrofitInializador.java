package com.example.dpitangui.myfirstapp;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by dpitangui on 01/03/2018.
 */

public class RetrofitInializador {

    private final Retrofit retrofit;

    public RetrofitInializador() {
        retrofit = new Retrofit.Builder().baseUrl(AppConfig.URL_BASE)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public LoginService Login() {
        return retrofit.create(LoginService.class);
    }

    public ProdutoService Produto(){
        return retrofit.create((ProdutoService.class));
    }
}
