package com.example.exam6.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exam6.R
import com.example.exam6.model.Card

class CardAdapter(var context: Context, var items : ArrayList<Card>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_card_view,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val card = items[position]

        if(holder is CardViewHolder){
            holder.apply {

                cardnumber.text = card.cardNumber
                cardholder.text = card.cardName
                expireDate.text = card.expireData
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class CardViewHolder(view : View) :RecyclerView.ViewHolder(view){
        var cardnumber = view.findViewById<TextView>(R.id.tv_cardNumber)
        var cardholder = view.findViewById<TextView>(R.id.tv_cardHolderName)
        var expireDate = view.findViewById<TextView>(R.id.tv_expiresDate)
    }
}