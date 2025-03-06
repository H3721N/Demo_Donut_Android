package com.gomez.herlin.logindemo.retrofit

import com.gomez.herlin.logindemo.dto.DonutsDto
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApiService {
    @get:GET("donuts")
    val donuts: Call<List<DonutsDto?>?>?
}
