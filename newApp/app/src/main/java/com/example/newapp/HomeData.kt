package com.example.newapp

import java.util.*

class HomeData(val bankName:String, val balAmount:Int, val accountNumber:String) {
}

class resList(
    val tranDate:String, val tranType:String, val afterBalanceAmt:Int,
    val inoutType:String,
    val printedContent:String,
    val branchName:String,
    val tranTime:String,
    val tranAmt:Int,
)

class CardList(
    val name:String,
    val company:String,
    val fee:String,
    val sortratio:MutableMap<String,Float>,
    var discountAmt:Float=0f
)

class category(
    val name:String,
    val content:ArrayList<String>
)

class news(
    val title:String,
    val content: String
)
