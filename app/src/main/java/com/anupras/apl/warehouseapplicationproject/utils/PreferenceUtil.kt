package com.anupras.apl.warehouseapplicationproject.utils

import android.content.Context
import com.anupras.apl.warehouseapplicationproject.utils.Constants

/**
 * Created by Anamika Painuly on 03/05/21.
 */
object PreferenceUtil {

    fun getUserId(context: Context): String? {
        val sharedPreferences =
            context.getSharedPreferences(Constants.PREF_USER_ID, Context.MODE_PRIVATE)
        return sharedPreferences.getString(Constants.PREF_USER_ID, null)
    }

    fun putUserId(context: Context, userId: String?) {
        val sharedPreferences =
            context.getSharedPreferences(Constants.PREF_USER_ID, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Constants.PREF_USER_ID, userId)
        editor.commit()
    }

}