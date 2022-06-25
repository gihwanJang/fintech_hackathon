package com.example.newapp

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingRate : AppCompatActivity() {
    private val bn: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navigation)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_rate)

        val submitBtn: Button = findViewById(R.id.paymentSubmit)
        val consum_rate_progress:TextView = findViewById(R.id.consum_rate_progress)
        val consumSeekBar:SeekBar = findViewById(R.id.consumSeekBar)
        val investment_rate_progress:TextView = findViewById(R.id.investment_rate_progress)
        val investmentSeekBar:SeekBar = findViewById(R.id.investmentSeekBar)
        val emergencyFund_rate_progress:TextView = findViewById(R.id.emergencyFund_rate_progress)
        val emergencyFundSeekBar:SeekBar = findViewById(R.id.emergencyFundSeekBar)

        submitBtn.setOnClickListener {
            val inputPay:EditText = findViewById(R.id.payEditText)
            val consumRate:Int = Integer.parseInt(consum_rate_progress.text.toString())
            val investmentRate:Int = Integer.parseInt(investment_rate_progress.text.toString())
            val emergencyFundRate:Int = Integer.parseInt(emergencyFund_rate_progress.text.toString())
            val pay = Integer.parseInt(inputPay.text.toString())
            val rate_apply_consum_text:TextView = findViewById(R.id.rate_apply_consum_text)
            val rate_apply_investment_text:TextView = findViewById(R.id.rate_apply_investment_text)
            val rate_apply_emergencyFund_text:TextView = findViewById(R.id.rate_apply_emergencyFund_text)
            rate_apply_consum_text.text = (pay/(consumRate+investmentRate+emergencyFundRate)*(consumRate)).toString()
            rate_apply_investment_text.text = (pay/(consumRate+investmentRate+emergencyFundRate)*(investmentRate)).toString()
            rate_apply_emergencyFund_text.text = (pay/(consumRate+investmentRate+emergencyFundRate)*(emergencyFundRate)).toString()
        }

        consumSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                consum_rate_progress.text = p1.toString()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        investmentSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                investment_rate_progress.text = p1.toString()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        emergencyFundSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                emergencyFund_rate_progress.text = p1.toString()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

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

}