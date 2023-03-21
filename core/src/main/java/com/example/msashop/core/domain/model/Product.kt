package com.example.msashop.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Product(
    var id: Int,
    var title: String,
    var price: String,
    var category: String,
    var description: String,
    var image: String,
        ):Parcelable
