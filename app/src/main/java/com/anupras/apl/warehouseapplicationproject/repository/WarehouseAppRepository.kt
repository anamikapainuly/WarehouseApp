package com.anupras.apl.warehouseapplicationproject.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.anupras.apl.warehouseapplicationproject.api.WarehouseApiService
import com.anupras.apl.warehouseapplicationproject.data.ProductDetail
import com.anupras.apl.warehouseapplicationproject.ui.searchFragment.pagingSource.WarehousePagingSource
import com.anupras.apl.warehouseapplicationproject.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WarehouseAppRepository @Inject constructor(private val warehouseApiService: WarehouseApiService) {

    //Fetching search data
    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { WarehousePagingSource(warehouseApiService, query) }
        ).liveData


    private val itemPrice: MutableLiveData<ProductDetail?> =
        MutableLiveData<ProductDetail?>() //or new it in constructor

    fun getItemPrice(query: String) {
        val paramsMap: MutableMap<String, String> = HashMap<String, String>()
        paramsMap["Barcode"] = query
        paramsMap["MachineID"] = Constants.MACHINE_ID
        paramsMap["UserID"] = Constants.PREF_USER_ID.toString()
        paramsMap["Branch"] = Constants.BRANCH_ID
        Log.d("Response--", query)

        warehouseApiService.getProductDetail(paramsMap as HashMap<String, String>)
            .enqueue(object : Callback<ProductDetail?> {
                override fun onFailure(call: Call<ProductDetail?>, t: Throwable) {
                    Log.d("Response Error--", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ProductDetail?>,
                    response: Response<ProductDetail?>
                ) {
                    Log.d("Response--", response.body().toString())
                    if (response.body() != null) {
                        itemPrice.value = response.body()

                        val response = response.body()
                        if (response?.Found == "N")
                            Log.d("Response--", "Product not found!")


                    } else {

                    }
                }
            })
    }


    fun getItemPriceData(): LiveData<ProductDetail?>? {
        return itemPrice
    }

}
