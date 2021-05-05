package com.anupras.apl.warehouseapplicationproject.ui.searchFragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.anupras.apl.warehouseapplicationproject.repository.WarehouseAppRepository


class SearchViewModel
@ViewModelInject constructor(
    private val repository: WarehouseAppRepository
) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val product = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }


    fun searchProduct(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "cloths"
    }
}