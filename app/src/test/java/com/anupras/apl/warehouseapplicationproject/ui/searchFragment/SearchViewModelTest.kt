package com.anupras.apl.warehouseapplicationproject.ui.searchFragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anupras.apl.warehouseapplicationproject.repository.WarehouseAppRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

/**
 * Created by Anamika Painuly on 05/05/21.
 */

// 1
@RunWith(JUnit4::class)
class SearchViewModelTest
{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: SearchViewModel
    //private lateinit var warehouseAppRepository: WarehouseAppRepository
    @Before
    fun setup(){
        //viewModel = SearchViewModel(warehouseAppRepository)
    }

    @Test
    fun searchWithNoValueReturnsError(){
        val str1: String = ""
        val str2 = "cloths"

//        clothsviewModel.searchProduct(str1)
//        Assert.assertNotNull("The object you enter return null", str1)
    }
}