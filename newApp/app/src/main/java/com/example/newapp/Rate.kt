package com.example.newapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class Rate : AppCompatActivity() {
    private val bn: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navigation)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)

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
}