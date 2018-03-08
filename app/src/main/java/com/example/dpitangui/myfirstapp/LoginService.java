package com.example.dpitangui.myfirstapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dpitangui on 01/03/2018.
 */

public interface LoginService {

    @POST("usuario.php")
    Call<List<Usuario>> login(@Query("usuario") String usuario, @Query("senha") String senha);
}
