package com.anupras.apl.warehouseapplicationproject

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Anamika Painuly on 03/05/21.
 */
@HiltAndroidApp
class WarehouseApplication : Application() {
    private var warehouseApplication: Context? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: WarehouseApplication
            private set
    }
}