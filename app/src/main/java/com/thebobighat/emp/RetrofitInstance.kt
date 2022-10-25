package com.thebobighat.emp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://tdg-apiv1.herokuapp.com/api/v1/emp/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}