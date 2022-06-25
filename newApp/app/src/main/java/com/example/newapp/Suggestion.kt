package com.example.newapp

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray

class Suggestion : AppCompatActivity() {
    private val bn: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navigation)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestion)

        val listView = findViewById<ListView>(R.id.suggestion_list)

        bn.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.ic_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                }
                R.id.ic_rate -> {
                    val intent = Intent(this, Rate::class.java)
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                }
                R.id.ic_suggestion ->{
                    val intent = Intent(this, Suggestion::class.java)
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                }
                R.id.ic_stock ->{
                    val intent = Intent(this, Stock::class.java)
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                }
            }
            true
        }

        val data = getData()

        val dataList = getList(data);

        val adapter = SuggestionAdapter(this,dataList);

        listView.adapter = adapter


    }
    fun getData():JSONArray{
        val jsonString = JSONArray(assets.open("card.json").reader().readText())

        return jsonString
    }

    fun getList(data:JSONArray) : ArrayList<CardList> {

        val dataList = arrayListOf<CardList>()

        for (i in 0 until data.length() - 1) {
            val item = data.getJSONObject(i)
            val recommendCard = item.getString("name")
            val company = item.getString("company")
            val fee = item.getString("fee")

            val 미용 = item.getString("미용")
            val 식비 = item.getString("식비")
            val 교통자동차 = item.getString("교통·자동차")
            val 편의점마트잡화 = item.getString("편의점·마트·잡화")
            val 기타  = item.getString("기타")
            val 주거통신 = item.getString("주거·통신")
            val 취미여가 = item.getString("취미·여가")
            val 카페간식 = item.getString("카페·간식")

            var map = mapOf<String,Double>(
                "미용" to 미용.toDouble(),
                "식비" to 식비.toDouble(),
                "교통자동차" to 교통자동차.toDouble(),
                "편의점마트잡화" to 편의점마트잡화.toDouble(),
                "기타" to 기타.toDouble(),
                "주거통신" to 주거통신.toDouble(),
                "취미여가" to 취미여가.toDouble(),
                "카페간식" to 카페간식.toDouble()
            )

            val sortratio =  map.toList().sortedByDescending { it.second }.toMap() as MutableMap

            dataList.add(CardList(recommendCard, company, fee,sortratio))

        }

        return dataList
    }
}