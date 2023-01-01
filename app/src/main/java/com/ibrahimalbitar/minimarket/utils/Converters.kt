package com.ibrahimalbitar.minimarket.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) : List<String> {

        if (value.isEmpty()|| value == "null"){
            return Gson().fromJson("[]", arrayListOf<String>()::class.java).toList()
        }
       return Gson().fromJson(value, arrayListOf<String>()::class.java).toList()
    }
}

