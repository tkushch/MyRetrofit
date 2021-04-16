package com.example.myretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

public interface FinhubService {
    @GET("api/v1/users/symbol?exchange=US&token=c1srgeqad3ib92vhu1fg")
    Call<List<Symbol>> listSymbols();
}
