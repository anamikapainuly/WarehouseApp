package com.anupras.apl.warehouseapplicationproject.ui.scanFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScanBarcodeViewModel : ViewModel() {
    val progressState: LiveData<Boolean> get() = _progressState
    private val _progressState = MutableLiveData<Boolean>()

    val navigation: LiveData<NavDirections?> get() = _navigation
    private val _navigation = MutableLiveData<NavDirections?>()

    init {
        _progressState.value = false
    }

    fun searchBarcode(barcode: String) {
        _progressState.value = true
        viewModelScope.launch {
            delay(1000)

            _navigation.value =
                ScanBarcodeFragmentDirections.actionScanFragmentToSuccessFragment(barcode)
            _progressState.value = false
        }
    }

    fun doneNavigating() {
        _navigation.value = null
    }
}