package com.example.newapp

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        settingButton.setBackgroundColor(Color.WHITE)
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
            animateY(3000)
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
            animateY(3000)
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
        val leftXaxis2 = barChart1.axisLeft
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
        val type = ArrayList<String>()
        val colorList = ArrayList<Int>()
        val set : BarDataSet

        values.add(BarEntry(1.0f, 20.0f))
        values.add(BarEntry(2.0f, 30.0f))
        values.add(BarEntry(3.0f, 40.0f))

        type.add(" ")
        type.add("What")
        type.add("Who")
        type.add("How")

        colorList.add(Color.parseColor("#4DD0E1"))
        colorList.add(Color.parseColor("#FFF176"))
        colorList.add(Color.parseColor("#FF8A65"))

        if (barChart1.data != null && barChart1.data.dataSetCount > 1) {
            val chartData1 = barChart1.data
            val chartData2 = barChart2.data
            set = chartData1?.getDataSetByIndex(0) as BarDataSet
            set.values = values
            chartData1.notifyDataChanged()
            chartData2.notifyDataChanged()
            barChart1.notifyDataSetChanged()
            barChart2.notifyDataSetChanged()
        } else {
            set = BarDataSet(values, " ")
            set.colors = colorList
            set.setDrawValues(true)

            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set)

            val data = BarData(dataSets)
            barChart1.data = data
            barChart1.setVisibleXRange(1.0f,3.0f)
            barChart1.setFitBars(true)
            barChart2.data = data
            barChart2.setVisibleXRange(1.0f,3.0f)
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
}