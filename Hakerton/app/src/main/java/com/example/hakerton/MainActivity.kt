package com.example.hakerton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hakerton.fragments.HomeFragment
import com.example.hakerton.fragments.ListFragment
import com.example.hakerton.fragments.RateFragment
import com.example.hakerton.fragments.StockFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val bn: BottomNavigationView by lazy {
        findViewById(R.id.bottom_navigation)
    }
    private val homeFragment = HomeFragment()
    private val rateFragment = RateFragment()
    private val listFragment = ListFragment()
    private val stockFragment = StockFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)

        bn.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_rate -> replaceFragment(rateFragment)
                R.id.ic_list -> replaceFragment(listFragment)
                R.id.ic_stock -> replaceFragment(stockFragment)
                else->homeFragment
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
    }

}