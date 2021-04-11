package com.example.testmercadolibre.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InstallmentsModel(
    val quantity: Int,
    val amount: Double
):Parcelable