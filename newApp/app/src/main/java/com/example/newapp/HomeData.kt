package com.example.newapp

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
    val sortratio:MutableMap<String,Double>
)