package com.example.myretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface FinhubService {
    @GET("api/v1/stock/symbol")
    Call<List<Symbol>> listSymbols(@Query("token") String token, @Query("exchange") String exch);
}
