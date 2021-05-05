package com.anupras.apl.warehouseapplicationproject.utils

import com.anupras.apl.warehouseapplicationproject.WarehouseApplication

/**
 * Created by Anamika Painuly on 03/05/21.
 */
object Constants {
    const val HTTP_URL_ENDPOINT = "https://twg.azure-api.net/"
    var PREF_USER_ID = PreferenceUtil.getUserId(WarehouseApplication.instance)
    const val BRANCH_ID = "208"

    //Please add SUBSCRIPTION_KEY to gradle.properties
    //const val SUBSCRIPTION_KEY = "" // Please fill in the SUBSCRIPTION_KEY given to you here.
    const val MACHINE_ID = "1234567890"
}