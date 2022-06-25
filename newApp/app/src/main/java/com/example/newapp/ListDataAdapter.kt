package com.example.newapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListDataAdapter(val context: Context?, val DataList: ArrayList<resList>): BaseAdapter()  {
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
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_accountdata_item,null)

        val purchaseDate = view.findViewById<TextView>(R.id.purchaseDate)
        val purchaseMenu = view.findViewById<TextView>(R.id.purchaseMenu)
        val cardType = view.findViewById<TextView>(R.id.cardType)
        val withDrawl = view.findViewById<TextView>(R.id.withDrawal)
        val balance = view.findViewById<TextView>(R.id.balance)

        val data = DataList[p0]

        purchaseDate.text = data.tranDate
        purchaseMenu.text = data.printedContent
        cardType.text = data.tranType
        withDrawl.text = data.tranAmt.toString()
        balance.text = data.afterBalanceAmt.toString()

        return view
    }

}