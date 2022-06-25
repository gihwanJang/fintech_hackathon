package com.example.newapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class FixedPayAdapter (val context: Context?, val fixedPayList: ArrayList<List<String>>): BaseAdapter()  {
    override fun getCount(): Int {
        return fixedPayList.size
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_fixedpay_item,null)
        val purchase = view.findViewById<TextView>(R.id.purchaseTextView)
        val cost = view.findViewById<TextView>(R.id.costTextView)
        val date = view.findViewById<TextView>(R.id.dateTextView)

        purchase.text = fixedPayList[p0][0]
        cost.text = fixedPayList[p0][1]
        date.text = fixedPayList[p0][2]

        return view
    }

}