package com.example.testmercadolibre.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultModel (
    val id : String,
    val title : String,
    val price: String,
    val installments : InstallmentsModel,
    val shipping: ShippingModel
): Parcelable