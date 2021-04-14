package com.example.testmercadolibre.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DetailProductModel (
    val title :String,
    val price : Long,
    val initial_quantity: Int,
    val available_quantity: Int,
    val condition: String,
    val pictures: List<PictureURLModel>,
    val shipping : ShippingModel
): Parcelable