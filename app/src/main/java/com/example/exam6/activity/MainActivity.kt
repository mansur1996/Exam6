package com.example.exam6.activity

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam6.R
import com.example.exam6.adapter.CardAdapter
import com.example.exam6.database.CardDB
import com.example.exam6.helper.Logger
import com.example.exam6.model.Card
import com.example.ogabekbasepractise.database.CardRepository
import com.example.ogabekbasepractise.networking.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val TAG: String = MainActivity::class.java.simpleName

    private lateinit var recyclerView: RecyclerView
    private lateinit var cardList : ArrayList<Card>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun onResume() {
        super.onResume()

        if(isInternetAvailable()){
            //online
            getCards()


        }else{
            //offline

            getCardsFromDatabase()
        }
    }

    private fun initViews(){
        cardList = ArrayList()
        recyclerView = findViewById(R.id.rv_main)
        val addBtn = findViewById<ImageView>(R.id.iv_add)

        //Open Activity
        addBtn.setOnClickListener {
            startActivity(Intent(this, AddNewCardActivity::class.java))
        }

        if(isInternetAvailable()){
        //online
            getCards()

        }else{
            //offline

            getCardsFromDatabase()
        }
    }

    private fun getCards(){
        RetrofitHttp.apiService.getAllCards().enqueue(object: Callback<ArrayList<Card>> {
            override fun onResponse(
                call: Call<ArrayList<Card>>,
                response: Response<ArrayList<Card>>
            ) {
                if(response.body() != null){
                    Logger.d(TAG, "OnResponse : ${response.body().toString()}")
                    cardList.clear()
                    cardList.addAll(response.body()!!)
                    saveToDatabase(response.body()!!)
                    setRv(cardList)

                }else{
                    Logger.e(TAG, "OnResponse : null")

                }
            }

            override fun onFailure(call: Call<ArrayList<Card>>, t: Throwable) {
                Logger.e(TAG, "OnFailure : ${t.localizedMessage}")
            }

        })
    }

    private fun getCardsFromDatabase(){
        val repository = CardRepository(application)
        Logger.d("TAG", repository.getCards().toString())

        cardList.clear()

        for(i in repository.getCards()){
            cardList.add(Card(i.id, i.cardNumber, i.cardName, i.expireData, i.cvv, i.isActive))
        }

        setRv(cardList)
    }

    private fun saveToDatabase(response: ArrayList<Card>){

        val repository = CardRepository(application)

        repository.deleteCards()

        for (i in response){
            val cardDB = CardDB(i.id!!, i!!.cardNumber, i.cardName, i.expireData, i.cvv, i.isActive)
            repository.saveCard(cardDB)
        }
    }

    private fun isInternetAvailable(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return infoMobile!!.isConnected || infoWifi!!.isConnected
    }

    private fun setRv(cards : ArrayList<Card>){
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = CardAdapter(this, cards)
        recyclerView.adapter = adapter

    }
}