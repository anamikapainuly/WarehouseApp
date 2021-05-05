package com.anupras.apl.warehouseapplicationproject.api

import com.anupras.apl.warehouseapplicationproject.data.User
import com.anupras.apl.warehouseapplicationproject.data.ProductDetail
import com.anupras.apl.warehouseapplicationproject.data.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Anamika Painuly on 03/05/21.
 */
interface WarehouseApiService {


    @GET("bolt/newuser.json")
    fun getNewUserId(): Call<User>

    @GET("bolt/price.json")
    fun getProductDetail(@QueryMap paramMap: HashMap<String, String>): Call<ProductDetail>

    @GET("bolt/search.json")
    suspend fun getSearchResult(@QueryMap paramMap: HashMap<String, String>): SearchResult



//I used this approach before but then I planned to use dependency injection

//companion object {
//    operator fun invoke(): WarehouseApiService {
//
//        // Define the interceptor, add authentication headers
//        val interceptor = Interceptor { chain ->
//            val newRequest: Request =
//                chain.request().newBuilder().addHeader("Ocp-Apim-Subscription-Key", Constants.SUBSCRIPTION_KEY).build()
//            chain.proceed(newRequest)
//        }
//
//
//        val builder = OkHttpClient.Builder()
//        builder.interceptors().add(interceptor)
//        val client = builder.build()
//
//        return Retrofit.Builder()
//            .client(client)
//            .baseUrl(Constants.HTTP_URL_ENDPOINT)
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(WarehouseApiService::class.java)
//    }
//  }


}
