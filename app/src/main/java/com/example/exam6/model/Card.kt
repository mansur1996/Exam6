package com.example.exam6.model

import com.google.gson.annotations.SerializedName

data class Card (
    val id: String,
    @SerializedName("card_number")
    val cardNumber: String,
    @SerializedName("card_name")
    val cardName: String,
    @SerializedName("expire_date")
    val expireData: String,

    val cvv: Long,
    val isActive: Boolean,
)