package com.example.ogabekbasepractise.database

import android.app.Application
import com.example.exam6.database.CardDB
import com.example.exam6.helper.Logger
import com.example.exam6.manager.RoomManager

class CardRepository(application: Application) {
    val TAG : String = CardRepository::class.java.simpleName

    private val db = RoomManager.getDatabase(application)

    private val cardDao : CardDao = db!!.cardDao()

    fun getCards():List<CardDB>{
        Logger.d(TAG, "${cardDao.getCards()}")
        return cardDao.getCards()
    }

    fun saveCard(cardDB: CardDB){
        Logger.d(TAG, "Saved")
        cardDao.saveCard(cardDB)
    }

    fun deleteCards(){
        Logger.d(TAG, "Database cleared")
        cardDao.deleteAllCards()
    }
}