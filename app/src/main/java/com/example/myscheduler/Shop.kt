package com.example.myscheduler

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

@Serializable
data class Shop (
    val shop_name : String
)

fun getShops(resources: Resources): List<Shop> {
    val assetManager = resources.assets
    val inputStream = assetManager.open("shop_info.json")
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val str: String = bufferedReader.readText()
    val listType = object : TypeToken<List<Shop>>() {}.type
    val obj = Gson().fromJson<List<Shop>>(str, listType)
    return obj
}

