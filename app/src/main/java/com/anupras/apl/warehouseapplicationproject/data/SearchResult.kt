package com.anupras.apl.warehouseapplicationproject.data

import android.os.Parcelable
import com.anupras.apl.warehouseappmodule.data.Products
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    val HitCount : Int,
    val Results : List<Results>,
    val SearchID : Int,
    val ProdQAT : String,
    val Found : String
): Parcelable

@Parcelize
data class Results (
    val Products : List<Products>,
    val Description : String
): Parcelable




