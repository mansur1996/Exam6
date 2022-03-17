package com.example.ogabekbasepractise.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exam6.database.CardDB

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCard(cardDB: CardDB)

    @Query("SELECT * FROM cards")
    fun getCards() : List<CardDB>

    @Query("DELETE FROM cards")
    fun deleteAllCards()

}