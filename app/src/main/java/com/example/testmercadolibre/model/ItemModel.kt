package com.example.testmercadolibre.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ItemModel (
    val title :String,
    val price : PriceModel,
    val discount: DiscountModel,
    val picture: PictureURLModel
): Parcelable