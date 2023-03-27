package com.example.marvel.ui.home


import com.example.mobiletask.api.ServiceBuilder


class CategoryRepository {

    suspend fun getCategorys() = ServiceBuilder.buildService().getCategorys()
    suspend fun getProcessType(cat:Int) = ServiceBuilder.buildService().getProcessType(cat)
}