package com.example.newapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class NewsAdapter(val context: Context, val newsList:ArrayList<news>): BaseAdapter() {
    override fun getCount(): Int {
        return newsList.size
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.news_item,null)

        val title = view.findViewById<TextView>(R.id.news_title)
        val content = view.findViewById<TextView>(R.id.news_content)

        title.text = newsList[p0].title
        content.text = newsList[p0].content

        return view
    }
}