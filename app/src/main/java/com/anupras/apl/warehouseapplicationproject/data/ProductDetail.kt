package com.anupras.apl.warehouseapplicationproject.data

import com.anupras.apl.warehouseappmodule.data.Products

/**
 * Created by Anamika Painuly on 03/05/21.
 */
data class ProductDetail(
    var Barcode: String? = null,
    var MachineID: String? = null,
    var Action: String? = null,
    var ScanBarcode: String? = null,
    var Products: Products? = null,
    var ScanID: String? = null,
    var UserDescription: String? = null,
    var ProdQAT: String? = null,
    var ScanDateTime: String? = null,
    var Found: String? = null,
    var UserID: String? = null,

    )