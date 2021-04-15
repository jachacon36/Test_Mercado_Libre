package com.example.testmercadolibre.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DiscountModel (
    val text :String
): Parcelable