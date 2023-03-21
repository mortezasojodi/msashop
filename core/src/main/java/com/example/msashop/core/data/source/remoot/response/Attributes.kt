package com.example.msashop.core.data.source.remoot.response

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("title")
    val title : String,

    @SerializedName("price")
    val price : String,

    @SerializedName("category")
    val category : String,

    @SerializedName("description")
    val description : String,

    @SerializedName("image")
    val image : String,


)

