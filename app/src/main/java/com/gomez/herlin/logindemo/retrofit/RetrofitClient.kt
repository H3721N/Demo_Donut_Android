package com.gomez.herlin.logindemo.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    const val URL: String = "https://private-d24209-ocisneros.apiary-mock.com/"

    val apiService: RetrofitApiService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!.create(RetrofitApiService::class.java)
        }
}
