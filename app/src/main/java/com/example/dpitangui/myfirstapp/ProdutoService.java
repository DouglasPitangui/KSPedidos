package com.example.dpitangui.myfirstapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dpitangui on 04/03/2018.
 */

public interface ProdutoService {

    @GET("pedido.php")
    Call<List<Produto>> obterProduto();
}
