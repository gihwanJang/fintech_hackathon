package com.example.newapp

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Rate : AppCompatActivity() {
    private val bn: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navigation)
    }
    private lateinit var barChart1: HorizontalBarChart
    private lateinit var barChart2: HorizontalBarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)

        val settingButton:Button = findViewById(R.id.rating_setting_button)
        barChart1 = findViewById(R.id.barChart1)
        barChart2 = findViewById(R.id.barChart2)
        basicSetting()
        setxAxis()
        setLeftXaxis()
        setRightXaxis()
        createBarChart()

        val listView = findViewById<ListView>(R.id.fixedList)
        val adapter = FixedPayAdapter(this,dataSplit());
        listView.adapter = adapter

        settingButton.setBackgroundColor(Color.argb(100,239,239,239))
        settingButton.setOnClickListener {
            val intent = Intent(this, SettingRate::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
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
    private fun basicSetting() {
        barChart1.apply {
            description.isEnabled = false
            setMaxVisibleValueCount(3)
            setPinchZoom(false)
            setDrawBarShadow(false)
            setDrawGridBackground(false)
            setDrawBorders(false)
            legend.isEnabled = false
            setTouchEnabled(false)
            isDoubleTapToZoomEnabled = false
            animateY(1000)
        }
        barChart2.apply {
            description.isEnabled = false
            setMaxVisibleValueCount(3)
            setPinchZoom(false)
            setDrawBarShadow(false)
            setDrawGridBackground(false)
            setDrawBorders(false)
            legend.isEnabled = false
            setTouchEnabled(false)
            isDoubleTapToZoomEnabled = false
            animateY(1000)
        }
    }

    private fun setxAxis() {
        val xAxis1 = barChart1.xAxis
        val xAxis2 = barChart2.xAxis
        xAxis1.apply {
            setDrawGridLines(false)
            isEnabled = true
            position = XAxis.XAxisPosition.BOTTOM
            disableGridDashedLine()
            setDrawAxisLine(false)
        }
        xAxis2.apply {
            setDrawGridLines(false)
            isEnabled = true
            position = XAxis.XAxisPosition.BOTTOM
            disableGridDashedLine()
            setDrawAxisLine(false)
        }
    }

    private fun setLeftXaxis() {
        val leftXaxis1 = barChart1.axisLeft
        val leftXaxis2 = barChart2.axisLeft
        leftXaxis1.apply {
            setDrawGridLines(false)
            setDrawAxisLine(false)
            isEnabled = false
            setDrawLabels(false)
        }
        leftXaxis2.apply {
            setDrawGridLines(false)
            setDrawAxisLine(false)
            isEnabled = false
            setDrawLabels(false)
        }
    }

    private fun setRightXaxis() {
        val rightXaxis1 = barChart1.axisRight
        val rightXaxis2 = barChart2.axisRight
        rightXaxis1.apply {
            setDrawGridLines(false)
            setDrawAxisLine(false)
            isEnabled = false
            setDrawLabels(false)
        }
        rightXaxis2.apply {
            setDrawGridLines(false)
            setDrawAxisLine(false)
            isEnabled = false
            setDrawLabels(false)
        }
    }
    private fun createBarChart() {
        val values = ArrayList<BarEntry>()
        val values2 = ArrayList<BarEntry>()
        val type = ArrayList<String>()
        val type2 = ArrayList<String>()
        val colorList = ArrayList<Int>()
        val set : BarDataSet
        val set2 : BarDataSet
        val map = getMap(1)
        var idx=1f
        type.add(" ")
        for((k,v) in map){
            values.add(BarEntry(idx++, v))
            type.add(k)
            type2.add(k)
        }

        values2.add(BarEntry(8.0f, 35000.0f))
        values2.add(BarEntry(7.0f, 30000.0f))
        values2.add(BarEntry(6.0f, 50000.0f))
        values2.add(BarEntry(5.0f, 50000.0f))
        values2.add(BarEntry(4.0f, 40000.0f))
        values2.add(BarEntry(3.0f, 30000.0f))
        values2.add(BarEntry(2.0f, 28000.0f))
        values2.add(BarEntry(1.0f, 10000.0f))

        colorList.add(Color.parseColor("#fab1a0"))
        colorList.add(Color.parseColor("#ffeaa7"))
        colorList.add(Color.parseColor("#e17055"))
        colorList.add(Color.parseColor("#fdcb6e"))
        colorList.add(Color.parseColor("#00cec9"))
        colorList.add(Color.parseColor("#81ecec"))
        colorList.add(Color.parseColor("#74b9ff"))
        colorList.add(Color.parseColor("#a29bfe"))


        if (barChart1.data != null && barChart1.data.dataSetCount > 1) {
            val chartData1 = barChart1.data
            val chartData2 = barChart2.data
            set = chartData1?.getDataSetByIndex(0) as BarDataSet
            set2 = chartData2?.getDataSetByIndex(0)as BarDataSet
            set.values = values
            set2.values = values2
            chartData1.notifyDataChanged()
            chartData2.notifyDataChanged()
            barChart1.notifyDataSetChanged()
            barChart2.notifyDataSetChanged()
        } else {
            set = BarDataSet(values, " ")
            set.colors = colorList
            set.setDrawValues(true)
            set2 = BarDataSet(values2, " ")
            set2.colors = colorList
            set2.setDrawValues(true)

            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set)
            val dataSets2 = ArrayList<IBarDataSet>()
            dataSets2.add(set2)

            val data = BarData(dataSets)
            val data2 = BarData(dataSets2)
            barChart1.data = data
            barChart1.setVisibleXRange(1.0f,8.0f)
            barChart1.setFitBars(true)
            barChart2.data = data2
            barChart2.setVisibleXRange(1.0f,8.0f)
            barChart2.setFitBars(true)

            val xAxis1 = barChart1.xAxis
            xAxis1.apply {
                granularity = 1f
                isGranularityEnabled = true
                valueFormatter = IndexAxisValueFormatter(type)
            }
            val xAxis2 = barChart2.xAxis
            xAxis2.apply {
                granularity = 1f
                isGranularityEnabled = true
                valueFormatter = IndexAxisValueFormatter(type)
            }
            barChart1.invalidate()
            barChart2.invalidate()
        }
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

    private fun fixedPay(): ArrayList<String> {
        val matrix = Array(12,{Array(31, {ArrayList<String>()})})
        val result = ArrayList<String>()
        for (i in 0..11) {
            for (j in 0..30) {
                matrix[i][j] = ArrayList<String>()
            }
        }
        val res = getResData()
        res.sortBy { -it.tranDate.replace("-", "").toInt()}
        val current = res[0].tranDate.replace("-", "").toInt()
        val maxmonth = current % 10000 / 100
        for (a in res) {
            if (current - a.tranDate.replace("-", "").toInt() > 199) break
            val time = a.tranDate.replace("-", "").toInt() % 10000
            matrix[time / 100 - 1][time % 100 - 1].add(a.printedContent + " " + a.tranAmt)
        }
        for (i in maxmonth - 1 downTo maxmonth - 2) {
            for (j in 30 downTo 0) {
                if (matrix[i - 1][j].size != 0) {
                    for (tmp in matrix[i][j]) {
                        val a = tmp
                        for (tmp2 in matrix[i - 1][j]) {
                            val b = tmp2
                            if (a[0] == b[0]) {
                                result.add(tmp + " " + (1 + j))
                                break
                            }
                        }
                    }
                }
            }
        }
        return result
    }
    private fun dataSplit(): ArrayList<List<String>> {
        val result=ArrayList<List<String>>()
        val list = fixedPay()
        for(s in list){
            var word = s.split(" ")
            result.add(word)
        }
        return result
    }
}