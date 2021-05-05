package com.anupras.apl.warehouseappmodule.data

import android.os.Parcelable
import com.anupras.apl.warehouseapplicationproject.data.Price
import kotlinx.android.parcel.Parcelize

@Parcelize
class Products(

    var ItemDescription: String? = null,
    var ImageURL: String? = null,
    var Description: String? = null,

    var Class0: String? = null,
    var Price: Price? = null,
    var Barcode: String? = null,

    var DeptID: String? = null,
    var SubClass: String? = null,
    var Class0ID: String? = null,
    var SubDeptID: String? = null,

    var BranchPrice: String? = null,
    var ItemCode: String? = null,
    var SubDept: String? = null,
    var ClassID: String? = null,
    var Dept: String? = null,
    var SubClassID: String? = null,
    var Class: String? = null,
    var ProductKey: String? = null
): Parcelable