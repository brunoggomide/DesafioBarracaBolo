package com.example.desafiobolos.DAO;

import com.example.desafiobolos.models.Cardapio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONCardapio {

    @GET("api/Cardapio")
    Call<List<Cardapio>> getCardapio();
}
