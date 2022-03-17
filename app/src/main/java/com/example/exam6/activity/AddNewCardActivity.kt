package com.example.exam6.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exam6.R
import com.example.exam6.model.Card
import com.example.ogabekbasepractise.networking.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNewCardActivity : AppCompatActivity() {
    lateinit var cardNumber: EditText
    lateinit var cardName: EditText
    lateinit var expireDate: EditText
    lateinit var svv: EditText
    lateinit var addBtn: Button
    var id = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_card)
        initViews()
    }

    private fun initViews() {
        cardNumber = findViewById(R.id.et_cardNumber)
        cardName = findViewById(R.id.et_cardName)
        expireDate = findViewById(R.id.et_expireDate)
        svv = findViewById(R.id.et_svv)
        addBtn = findViewById(R.id.btn_add)

        addBtn.setOnClickListener {

            if(cardNumber.text.isNotEmpty() && cardName.text.isNotEmpty() && expireDate.text.isNotEmpty() && svv.text.isNotEmpty()){
                val card = Card(
                    id.toString(),
                    cardNumber.text.toString(),
                    cardName.text.toString(),
                    expireDate.text.toString(),
                    svv.text.toString().toLong(),
                    true
                )
                addToDatabase(card)

            }else{
                Toast.makeText(applicationContext, "Fill of fields", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun addToDatabase(card: Card) {
        RetrofitHttp.apiService.createCard(card).enqueue(object : Callback<Card> {
            override fun onResponse(call: Call<Card>, response: Response<Card>) {
                Toast.makeText(applicationContext, "Successfully Added", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<Card>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}