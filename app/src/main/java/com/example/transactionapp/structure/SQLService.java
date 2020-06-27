package com.example.transactionapp.structure;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SQLService {

    @GET("/go")
    Call<List<Transaction>> listTransactions();
}
