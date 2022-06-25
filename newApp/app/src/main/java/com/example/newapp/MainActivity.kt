package com.example.newapp

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    private lateinit var pieChart: PieChart
    private lateinit var pieChart2: PieChart
    private val bn: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navigation)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pButton:Button = findViewById(R.id.plus_button)
        val date = Date(SimpleDateFormat("yyyy-MM-dd").parse("2022-03-15").time)
        pieChart = findViewById(R.id.pieChart)
        pieChart2 = findViewById(R.id.pieChart2)
        initPieChart()
        initPieChart2()
        setDataToPieChart()
        setDataToPieChart2()

        val data = getData()
        val totalAmtList = arrayListOf<Int>()
        val adapter = HomeDataAdapter(this,data);
        val listView = findViewById<ListView>(R.id.listView)

        for(i in 0 until data.size-1){
            totalAmtList.add(data[i].balAmount)
        }

        intent.putExtra("totalAmtList",totalAmtList)

        pButton.setBackgroundColor(Color.BLACK)
        listView.adapter = adapter;

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Account::class.java)
            intent.putExtra("position",position)
            startActivity(intent)
        }

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
    }

    private fun initPieChart() {
        pieChart.setUsePercentValues(true)
        pieChart.description.text = ""
        pieChart.isDrawHoleEnabled = false
        pieChart.setTouchEnabled(false)
        pieChart.setDrawEntryLabels(false)
        pieChart.setExtraOffsets(20f, 0f, 20f, 20f)
        pieChart.setUsePercentValues(true)
        pieChart.isRotationEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
        pieChart.legend.isWordWrapEnabled = true
    }
    private fun initPieChart2() {
        pieChart2.setUsePercentValues(true)
        pieChart2.description.text = ""
        pieChart2.isDrawHoleEnabled = false
        pieChart2.setTouchEnabled(false)
        pieChart2.setDrawEntryLabels(false)
        pieChart2.setExtraOffsets(20f, 0f, 20f, 20f)
        pieChart2.setUsePercentValues(true)
        pieChart2.isRotationEnabled = false
        pieChart2.setDrawEntryLabels(false)
        pieChart2.legend.orientation = Legend.LegendOrientation.VERTICAL
        pieChart2.legend.isWordWrapEnabled = true
    }
    private fun setDataToPieChart() {
        pieChart.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()
        val valuMap = getMap(2)

        for((k,v) in valuMap){
            println(v)
            dataEntries.add(PieEntry(v, k))
            println()
        }
        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#fab1a0"))
        colors.add(Color.parseColor("#ffeaa7"))
        colors.add(Color.parseColor("#e17055"))
        colors.add(Color.parseColor("#fdcb6e"))
        colors.add(Color.parseColor("#00cec9"))
        colors.add(Color.parseColor("#81ecec"))
        colors.add(Color.parseColor("#74b9ff"))
        colors.add(Color.parseColor("#a29bfe"))


        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 1f
        dataSet.colors = colors
        pieChart.data = data
        data.setValueTextSize(14f)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart.animateY(1400, Easing.EaseInOutQuad)
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        pieChart.setDrawCenterText(true);
        pieChart.centerText = "지난 달 소비비율"
        pieChart.invalidate()
    }
    private fun setDataToPieChart2() {
        pieChart2.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()
        val valuMap = getMap(1)

        for((k,v) in valuMap)
            dataEntries.add(PieEntry(v, k))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#fab1a0"))
        colors.add(Color.parseColor("#ffeaa7"))
        colors.add(Color.parseColor("#e17055"))
        colors.add(Color.parseColor("#fdcb6e"))
        colors.add(Color.parseColor("#00cec9"))
        colors.add(Color.parseColor("#81ecec"))
        colors.add(Color.parseColor("#74b9ff"))
        colors.add(Color.parseColor("#a29bfe"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 1f
        dataSet.colors = colors
        pieChart2.data = data
        data.setValueTextSize(14f)
        pieChart2.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart2.animateY(1400, Easing.EaseInOutQuad)
        pieChart2.holeRadius = 58f
        pieChart2.transparentCircleRadius = 61f
        pieChart2.isDrawHoleEnabled = true
        pieChart2.setHoleColor(Color.WHITE)
        pieChart2.setDrawCenterText(true);
        pieChart2.centerText = "이번 달 소비비율"
        pieChart2.invalidate()
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

    private fun getData():ArrayList<HomeData> {

        val listdata = ArrayList<HomeData>();

        val jsonString = assets.open("transaction.json").reader().readText();
        val jObject = JSONObject(jsonString);
        val data = JSONArray(jObject.getString("account"))

        for(i in 0 until data.length()){
            val jsonObject = data.getJSONObject(i)

            val bankName = jsonObject.getString("bank_name");
            val balanceAmt = jsonObject.getString("balance_amt");
            val accountNumber = jsonObject.getString("account_number");

            listdata.add(HomeData(bankName, balanceAmt.toInt(),accountNumber))
        }
        return listdata;
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

    fun getTransactionDate(date: Date): JSONObject {
        var jsonString = JSONObject(assets.open("transaction.json").reader().readText())
        val base = date.time
        var accounts = jsonString.getJSONArray("account")
        for(i in 0 until accounts.length()) {
            var res_list = accounts.getJSONObject(i).getJSONArray("res_list")
            for(j in res_list.length() - 1 downTo 0) {
                var res = res_list.getJSONObject(j)
                var t1 = SimpleDateFormat("yyyy-MM-dd").parse(res.getString("tran_date")).time
                if(t1 > base)
                    res_list.remove(j)
            }
        }
        Log.d("transcation", jsonString.toString(4))
        return jsonString
    }

}