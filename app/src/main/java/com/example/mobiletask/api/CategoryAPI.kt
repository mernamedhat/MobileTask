package com.example.mobiletask.api


import com.example.mobiletask.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryAPI {
    @GET("get_all_cats")
    suspend fun  getCategorys(): Response
    @GET("properties")
    suspend fun  getProcessType(
        @Query("cat") cat: Int,
    ): ProcessTypeModel
}