package com.example.ogabekbasepractise.networking.service

import com.example.exam6.model.Card
import retrofit2.Call
import retrofit2.http.*

interface CardService {

    @GET("users")
    fun getAllCards() : Call<ArrayList<Card>>

    @POST("users")
    fun createCard(@Body card: Card) : Call<Card>

    @DELETE("users/{id}")
    fun deleteCard(@Path("id") id : Int) : Call<Card>

}