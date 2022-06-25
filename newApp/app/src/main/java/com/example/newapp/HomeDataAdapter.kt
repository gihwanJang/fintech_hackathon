package com.example.newapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class HomeDataAdapter(val context: Context?, val DataList: ArrayList<HomeData>): BaseAdapter()  {
    override fun getCount(): Int {
        return DataList.size
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view:View = LayoutInflater.from(context).inflate(R.layout.list_homedata_item,null)

        val bankName = view.findViewById<TextView>(R.id.bankName)
        val balAmount = view.findViewById<TextView>(R.id.balAmount)
        val accountNum = view.findViewById<TextView>(R.id.accountNum)

        val data = DataList[p0]

        bankName.text = data.bankName
        balAmount.text = "%,d".format(data.balAmount)
        accountNum.text = data.accountNumber

        return view
    }

}