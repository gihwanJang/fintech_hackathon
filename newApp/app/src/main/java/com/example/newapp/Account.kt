package com.example.newapp

import android.app.ActivityOptions
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ListView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray
import org.json.JSONObject

class Account : AppCompatActivity() {
    private val bn: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navigation)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
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




        val position = intent.getIntExtra("position",0)
        val totalAmt = getTotalAmt(position)


        val resList = JSONArray(getData(position))
        val resListData = arrayListOf<resList>()

        for(i in 0 until resList.length()){

            val item = resList.getJSONObject(i)

            val tranDate = item.getString("tran_date")
            val tranType = item.getString("tran_type")
            val afterBalanceAmt = item.getString("after_balance_amt")
            val inoutType = item.getString("inout_type")
            val printedContent = item.getString("printed_content")
            val branchName = item.getString("branchName")
            val tranTime = item.getString("tran_time")
            val tranAmt = item.getString("tran_amt")

            resListData.add(resList(tranDate,tranType, afterBalanceAmt.toInt(),inoutType,printedContent,branchName,tranTime,tranAmt.toInt()))
        }


        getData(position)


        val adapter = ListDataAdapter(this,resListData);
        val listView = findViewById<ListView>(R.id.acount_list)
        listView.adapter = adapter

    }
    fun getData(position:Int):String {

        val jsonString = assets.open("test1.json").reader().readText();
        val jObject = JSONArray(JSONObject(jsonString).getString("account"));

        val data = jObject.getJSONObject(position).getString("res_list");

        return data
    }

    fun getTotalAmt(position:Int):String{
        val jsonString = assets.open("test1.json").reader().readText();
        val jObject = JSONArray(JSONObject(jsonString).getString("account"));

        return jObject.getJSONObject(position).getString("balance_amt")
    }
}