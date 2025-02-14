package com.gomez.herlin.logindemo.retrofit;

import com.gomez.herlin.logindemo.dto.DonutsDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiService {

    @GET("donuts")
    Call<List<DonutsDto>> getDonuts();
}
