package com.example.newapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private  lateinit var listButton: Button
    private lateinit var pieChart: PieChart
    private lateinit var pieChart2: PieChart
    private val bn: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navigation)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pieChart = findViewById(R.id.pieChart)
        pieChart2 = findViewById(R.id.pieChart2)
        initPieChart()
        initPieChart2()
        setDataToPieChart()
        setDataToPieChart2()

        bn.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.ic_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_rate -> {
                    val intent = Intent(this, Rate::class.java)
                    startActivity(intent)
                }
                R.id.ic_suggestion ->{
                    val intent = Intent(this, Suggestion::class.java)
                    startActivity(intent)
                }
                R.id.ic_stock ->{
                    val intent = Intent(this, Stock::class.java)
                    startActivity(intent)
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
        dataEntries.add(PieEntry(30f, "쇼핑"))
        dataEntries.add(PieEntry(30f, "식비"))
        dataEntries.add(PieEntry(40f, "고정"))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFF176"))
        colors.add(Color.parseColor("#FF8A65"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
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
        pieChart.centerText = "지난 달 자산비율"
        pieChart.invalidate()
    }
    private fun setDataToPieChart2() {
        pieChart2.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(30f, "쇼핑"))
        dataEntries.add(PieEntry(30f, "식비"))
        dataEntries.add(PieEntry(40f, "고정"))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFF176"))
        colors.add(Color.parseColor("#FF8A65"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
        dataSet.colors = colors
        pieChart2.data = data
        data.setValueTextSize(15f)
        pieChart2.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart2.animateY(1400, Easing.EaseInOutQuad)
        pieChart2.holeRadius = 58f
        pieChart2.transparentCircleRadius = 61f
        pieChart2.isDrawHoleEnabled = true
        pieChart2.setHoleColor(Color.WHITE)
        pieChart2.setDrawCenterText(true);
        pieChart2.centerText = "이번 달 자산비율"
        pieChart2.invalidate()
    }
}