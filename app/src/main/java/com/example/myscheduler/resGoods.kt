package com.example.myscheduler

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader

@Serializable
data class resGoods (
        val goods_name : String
)

fun getresGoods(resources: Resources): List<resGoods> {
    val assetManager = resources.assets
    val inputStream = assetManager.open("resgoods_info.json")
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val str: String = bufferedReader.readText()
    val listType = object : TypeToken<List<resGoods>>() {}.type
    val obj = Gson().fromJson<List<resGoods>>(str, listType)
    return obj
}