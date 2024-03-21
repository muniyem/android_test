package com.example.androidtest.database

import androidx.room.TypeConverter
import com.example.androidtest.models.MoviesItem
//import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
//import com.tplcorp.tplLifeRevamp.Api.ApiResponses.HomeApiResponses.Product.Feature

object DataConverters {
//    @TypeConverter
//    @JvmStatic
//    fun fromProductFeatureList(countryLang: List<Feature?>?): String? {
//        if (countryLang == null) {
//            return null
//        }
//        val gson = Gson()
//        val type = object : TypeToken<List<Feature?>?>() {}.type
//        return gson.toJson(countryLang, type)
//    }

    @TypeConverter
    @JvmStatic
    fun genereList(countryLangString: String?): List<Int>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Int?>?>() {}.type
        return gson.fromJson<List<Int>>(countryLangString, type)
    }

//    @TypeConverter
//    @JvmStatic
//    fun moviesItemList(): List<MoviesItem>? {
//        if (countryLangString == null) {
//            return null
//        }
//        val gson = Gson()
//        val type = object : TypeToken<List<MoviesItem?>?>() {}.type
//        return gson.fromJson<List<MoviesItem>>( type)
//    }
}