package com.example.boxesaplication.prefrence

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("Cat-pref", Context.MODE_PRIVATE)

    fun saveCategoryId(value: Int) {
        preferences.edit().putInt(CatId, value).apply()
    }

    fun getCategoryId() = preferences.getInt(CatId, 0)



    fun saveCategoryname(value: String) {
        preferences.edit().putString(Catname, value).apply()
    }

    fun getCategoryname() = preferences.getString(Catname, " ")

    companion object {
        const val CatId = "Cat_id"
        const val Catname = "Cat_Name"
    }

}