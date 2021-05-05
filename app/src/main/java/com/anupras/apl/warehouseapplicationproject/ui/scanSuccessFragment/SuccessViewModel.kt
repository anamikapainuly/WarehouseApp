package com.anupras.apl.warehouseapplicationproject.ui.scanSuccessFragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.anupras.apl.warehouseapplicationproject.data.ProductDetail
import com.anupras.apl.warehouseapplicationproject.repository.WarehouseAppRepository


/**
 * Created by Anamika Painuly on 05/05/21.
 */
class SuccessViewModel @ViewModelInject constructor(
    private val repository: WarehouseAppRepository
) : ViewModel() {

    //API Call data send
    fun getProductPrice(query: String) {
        repository.getItemPrice(query)
    }


    var productData: MutableLiveData<ProductDetail>? = null

    fun getProductDetail(): LiveData<ProductDetail>? {
        productData = repository.getItemPriceData() as MutableLiveData<ProductDetail>?
        return productData
    }


}