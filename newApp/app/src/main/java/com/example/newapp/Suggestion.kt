package com.example.newapp

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


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

        val cardList = getCardRecommend(dataList)

        val adapter = SuggestionAdapter(this,cardList);

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

            var map = mapOf<String,Float>(
                "미용" to 미용.toFloat(),
                "식비" to 식비.toFloat(),
                "교통·자동차" to 교통자동차.toFloat(),
                "편의점·마트·잡화" to 편의점마트잡화.toFloat(),
                "기타" to 기타.toFloat(),
                "주거·통신" to 주거통신.toFloat(),
                "취미·여가" to 취미여가.toFloat(),
                "카페·간식" to 카페간식.toFloat()
            )

            val sortratio =  map.toList().sortedByDescending { it.second }.toMap() as MutableMap

            dataList.add(CardList(recommendCard, company, fee,sortratio))

        }

        return dataList
    }

    private fun getPieChartData(): ArrayList<category> {
        val listdat = ArrayList<category>();

        val jsonString = assets.open("category.json").reader().readText();

        val jObject = JSONObject(jsonString);
        val data = JSONArray(jObject.getString("account"))



        for(i in 0 until data.length()){
            val jsonObject = data.getJSONObject(i)
            val obj = jsonObject.getJSONArray("content")
            var list = ArrayList<String>()
            for (j in 0 until obj.length()) {
                list.add(obj.getString(j))
            }


            listdat.add(category(jsonObject.getString("category"),list))
        }

        return listdat
    }

    private fun getResData():ArrayList<resList>{

        val resListData = ArrayList<resList>();

        val jsonString = assets.open("transaction.json").reader().readText();
        val jObject = JSONObject(jsonString);
        val data = JSONArray(jObject.getString("account"))

        for(i in 0 until data.length()){
            val jsonObject = data.getJSONObject(i)
            val obj = jsonObject.getJSONArray("res_list")
            for(j in 0 until obj.length()) {
                val obb = obj.getJSONObject(j)
                val tranDate = obb.getString("tran_date")
                val tranType = obb.getString("tran_type")
                val afterBalanceAmt = obb.getString("after_balance_amt")
                val inoutType = obb.getString("inout_type")
                val printedContent = obb.getString("printed_content")
                val branchName = obb.getString("branchName")
                val tranTime = obb.getString("tran_time")
                val tranAmt = obb.getString("tran_amt")
                resListData.add(resList(tranDate,tranType, afterBalanceAmt.toInt(),inoutType,printedContent,branchName,tranTime,tranAmt.toInt()))
            }
        }
        return resListData;
    }
    public fun getMap(type:Int): HashMap<String, Float> {
        val map = HashMap<String,Float>()
        val categoryList = getPieChartData()
        val resList = getResData()

        for(i in 0 until categoryList.size)
            map.put(categoryList.get(i).name,0f)

        val curr = resList.get(resList.size-1)
        val next = curr.tranDate.substring(5)

        val current = Integer.parseInt(next.replace("-",""))

        for(i in 0 until  resList.size){
            if (type == 1) {
                if (current - resList.get(i).tranDate.substring(5).replace("-", "").toInt() < 100) {
                    for (j in 0 until categoryList.size) {
                        if (categoryList.get(j).content.contains(resList.get(i).printedContent) && resList.get(i).tranAmt.toFloat() < 0) {
                            map[categoryList.get(j).name] = map[categoryList.get(j).name]!! - resList.get(i).tranAmt.toFloat()
                        }
                    }
                }
            } else {
                if (current - resList.get(i).tranDate.substring(5).replace("-", "").toInt() > 100
                    && current - resList.get(i).tranDate.substring(5).replace("-", "").toInt() < 200
                ) {
                    for (cate in categoryList) {
                        if (cate.content.contains(resList.get(i).printedContent) && resList.get(i).tranAmt.toFloat() < 0) {
                            map[cate.name] = map[cate.name]!! - resList.get(i).tranAmt.toFloat()
                        }
                    }
                }
            }
        }
        return map
    }

    //카드추천 함수
    fun getCardRecommend(dataList:ArrayList<CardList>): List<CardList> {
        val amount: HashMap<String, Float> = getMap(1)

        val card: ArrayList<CardList> = dataList

        for (i in card) {
            for ((key, value) in i.sortratio) {
                println("amount:${amount[key]}")
                println("value:${value}")
                i.discountAmt += value * amount[key]!! * 0.01f;
            }
        }

        var sortedList = card.sortedWith(compareBy { -it.discountAmt })

        return sortedList
    }

}