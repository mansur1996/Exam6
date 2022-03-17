package com.example.ogabekbasepractise.networking

import com.example.ogabekbasepractise.networking.service.CardService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHttp {
    companion object{
        private val TAG : String = RetrofitHttp::class.java.simpleName

        const val IS_TESTER : Boolean = true

        const val SERVER_DEVELOPMENT = "https://6221a4bcafd560ea69b5b545.mockapi.io/api/v1/"
        const val SERVER_PRODUCTION = "https://6221a4bcafd560ea69b5b545.mockapi.io/api/v1/"

        private fun server() : String{
            return if(IS_TESTER) {
                SERVER_DEVELOPMENT
            }else{
                SERVER_PRODUCTION
            }
        }

        private fun getRetrofit() : Retrofit{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(server())
                .build()
        }

        val apiService: CardService = getRetrofit().create(CardService::class.java)
    }
}