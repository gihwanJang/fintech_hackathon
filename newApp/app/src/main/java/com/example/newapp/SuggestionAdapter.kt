package com.example.newapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SuggestionAdapter(val context: Context?, val RecommendList: ArrayList<CardList>): BaseAdapter()  {
    override fun getCount(): Int {
        return RecommendList.size
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_recommend_item,null)

        val recommendCard = view.findViewById<TextView>(R.id.recommend_card)
        val ratio1 = view.findViewById<TextView>(R.id.ratio1)
        val ratio2 = view.findViewById<TextView>(R.id.ratio2)
        val ratio3 = view.findViewById<TextView>(R.id.ratio3)
        val company = view.findViewById<TextView>(R.id.company)
        val fee = view.findViewById<TextView>(R.id.fee)

        recommendCard.text = RecommendList[p0].name
        fee.text = RecommendList[p0].fee
        company.text = RecommendList[p0].company

        var num = 0;

        for((key,value) in RecommendList[p0].sortratio){
            if(num == 0) {
                ratio1.text = "${key} : ${value}%"
                num++
            }else if(num == 1) {
                ratio2.text = "${key} : ${value}%"
                num++
            }else if(num == 2){
                ratio3.text = "${key} : ${value}%"
                num++
            }
        }

        return view
    }

}