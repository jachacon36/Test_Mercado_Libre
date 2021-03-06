package com.example.testmercadolibre.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ComponentModel (
    val type :String,
    val elements : List<ElementModel>,
    val items: List<ItemModel>
): Parcelable