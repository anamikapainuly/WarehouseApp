package com.anupras.apl.warehouseapplicationproject

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.anupras.apl.warehouseapplicationproject.data.User
import com.anupras.apl.warehouseapplicationproject.api.WarehouseApiService
import com.anupras.apl.warehouseapplicationproject.utils.PreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.String
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {
    private var navController: NavController? = null

    @Inject
    lateinit var warehouseApiService: WarehouseApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(navController!!.graph)
        NavigationUI.setupActionBarWithNavController(this, navController!!, appBarConfiguration)


    }


    override fun onResume() {
        super.onResume()


        if (PreferenceUtil.getUserId(this) == null) {

            warehouseApiService.getNewUserId().enqueue(object : Callback<User?> {
                override fun onResponse(call: Call<User?>, response: Response<User?>) {
                    if (response.isSuccessful) {
                        Log.d("UserId---", String.valueOf(response.body()))
                        val user = response.body()
                        if (user?.UserID != null) {
                            PreferenceUtil.putUserId(this@MainActivity, user.UserID)
                            Log.d("UserId---", user.UserID)
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Get User failed!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<User?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Get User failed!", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }
}

