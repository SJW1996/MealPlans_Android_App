package com.example.rankmystore

data class store_object (
    var storename: String = "",
    var address: String = "",
    var rating_score: Float,
    var url: String = ""
){
    constructor(): this("","",-1f, "")

}



