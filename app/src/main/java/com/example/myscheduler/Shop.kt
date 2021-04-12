package com.example.myscheduler

import android.content.res.Resources
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader

@Serializable
data class Shop (
    val name : String
    )

fun getShops(resources: Resources): List<Shop> {
    val assetManager = resources.assets
    val inputStream = assetManager.open("shop_info.json")
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val str: String = Json.encodeToString(bufferedReader.readText())
    val obj = Json.decodeFromString<List<Shop>>(str)
    return obj
}