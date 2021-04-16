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
data class Goods (
    val goods_name : String
)

fun getGoods(resources: Resources): List<Goods> {
    val assetManager = resources.assets
    val inputStream = assetManager.open("goods_info.json")
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val str: String = bufferedReader.readText()
    val listType = object : TypeToken<List<Goods>>() {}.type
    val obj = Gson().fromJson<List<Goods>>(str, listType)
    return obj
}