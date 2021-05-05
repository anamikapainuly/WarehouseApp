package com.anupras.apl.warehouseapplicationproject.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Anamika Painuly on 03/05/21.
 */

@Parcelize
data class Price (
    var Price: String? = null,
    var Type: String? = null) : Parcelable
