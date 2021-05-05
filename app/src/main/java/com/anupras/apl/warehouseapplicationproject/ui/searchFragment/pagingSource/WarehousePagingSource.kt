package com.anupras.apl.warehouseapplicationproject.ui.searchFragment.pagingSource


import androidx.paging.PagingSource
import com.anupras.apl.warehouseapplicationproject.api.WarehouseApiService
import com.anupras.apl.warehouseapplicationproject.data.Results
import com.anupras.apl.warehouseapplicationproject.utils.Constants
import retrofit2.HttpException
import java.io.IOException

class WarehousePagingSource(
    private val warehouseApiService: WarehouseApiService,
    private val query: String
) : PagingSource<Int, Results>() {
    private val DEFAULT_PAGE_INDEX = 0


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val paramsMap: MutableMap<String, String> = HashMap<String, String>()
            paramsMap["Search"] = query
            paramsMap["MachineID"] = Constants.MACHINE_ID
            paramsMap["UserID"] = Constants.PREF_USER_ID.toString()
            paramsMap["Branch"] = Constants.BRANCH_ID
            paramsMap["Start"] = 0.toString()
            paramsMap["Limit"] = 20.toString()
            val response = warehouseApiService.getSearchResult(paramsMap as HashMap<String, String>)
            LoadResult.Page(
                response.Results,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.Results.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}
