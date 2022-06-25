package com.example.newapp

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray

class Stock : AppCompatActivity() {
    private lateinit var pieChart: PieChart
    private lateinit var barChart: HorizontalBarChart
    private val bn: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navigation)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)
        getStockList()
        pieChart = findViewById(R.id.pieChart3)
        initPieChart()
        setDataToPieChart()

        barChart = findViewById(R.id.barChart3)
        basicSetting()
        setxAxis()
        setLeftXaxis()
        setRightXaxis()
        createBarChart()

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

    private fun setDataToPieChart() {
        pieChart.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()
        val map:HashMap<String,Int> = getStockList()
        for((k,v) in map)
            dataEntries.add(PieEntry(v.toFloat(),k))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFF176"))
        colors.add(Color.parseColor("#FF8A65"))
        colors.add(Color.parseColor("#74b9ff"))
        colors.add(Color.parseColor("#a29bfe"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 2f
        dataSet.colors = colors
        pieChart.data = data
        data.setValueTextSize(15f)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart.animateY(1400, Easing.EaseInOutQuad)
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        pieChart.setDrawCenterText(true);
        pieChart.centerText = "주식 보유 현황"
        pieChart.invalidate()
    }
    private fun basicSetting() {
        barChart.apply {
            description.isEnabled = false
            setMaxVisibleValueCount(3)
            setPinchZoom(false)
            setDrawBarShadow(false)
            setDrawGridBackground(false)
            setDrawBorders(false)
            legend.isEnabled = false
            setTouchEnabled(false)
            isDoubleTapToZoomEnabled = false
            animateY(3000)
        }
    }

    private fun setxAxis() {
        val xAxis = barChart.xAxis
        xAxis.apply {
            setDrawGridLines(false)
            isEnabled = true
            position = XAxis.XAxisPosition.BOTTOM
            disableGridDashedLine()
            setDrawAxisLine(false)
        }
    }

    private fun setLeftXaxis() {
        val leftXaxis = barChart.axisLeft
        leftXaxis.apply {
            setDrawGridLines(false)
            setDrawAxisLine(false)
            isEnabled = false
            setDrawLabels(false)
        }
    }

    private fun setRightXaxis() {
        val rightXaxis = barChart.axisRight
        rightXaxis.apply {
            setDrawGridLines(false)
            setDrawAxisLine(false)
            isEnabled = false
            setDrawLabels(false)
        }
    }
    private fun createBarChart() {
        val values = ArrayList<BarEntry>()
        val type = ArrayList<String>()
        val colorList = ArrayList<Int>()
        val set : BarDataSet

        values.add(BarEntry(1.0f, 20.0f))
        values.add(BarEntry(2.0f, 30.0f))
        values.add(BarEntry(3.0f, 40.0f))

        type.add(" ")
        type.add("KB")
        type.add("신한")
        type.add("NH")

        colorList.add(Color.parseColor("#4DD0E1"))
        colorList.add(Color.parseColor("#FFF176"))
        colorList.add(Color.parseColor("#FF8A65"))

        if (barChart.data != null && barChart.data.dataSetCount > 1) {
            val chartData = barChart.data
            set = chartData?.getDataSetByIndex(0) as BarDataSet
            set.values = values
            chartData.notifyDataChanged()
            barChart.notifyDataSetChanged()
        } else {
            set = BarDataSet(values, " ")
            set.colors = colorList
            set.setDrawValues(true)

            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set)

            val data = BarData(dataSets)
            barChart.data = data
            barChart.setVisibleXRange(1.0f,3.0f)
            barChart.setFitBars(true)

            val xAxis = barChart.xAxis
            xAxis.apply {
                granularity = 1f
                isGranularityEnabled = true
                valueFormatter = IndexAxisValueFormatter(type)
            }
            barChart.invalidate()
        }
    }
    fun getStockList(): HashMap<String, Int> {
        val jsonString = assets.open("stock.json").reader().readText();

        val stockAmt = HashMap<String, Int>()
        val jObject = JSONArray(jsonString)
        for (i in 0 until jObject.length()) {
            val obj = jObject.getJSONObject(i)
            val name = obj.getString("name")
            val price = obj.getInt("price")
            val count = obj.getInt("count")
            stockAmt[name] = price * count
            println(price*count)
        }
        return stockAmt
    }
}