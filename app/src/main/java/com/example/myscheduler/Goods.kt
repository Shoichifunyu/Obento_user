package com.example.myscheduler

import android.content.res.Resources
import android.graphics.Insets.add
import androidx.core.view.OneShotPreDrawListener.add
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import io.realm.log.RealmLog.add
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader

@Serializable
data class Goods (
    val goods_name : String,
    var imageName: String
)

data class Shops (
        val shop_name: String
)

data class GS(
    var goods_name : String,
    var shop_name: String,
    var imageName: String
)

fun getGoods(resources: Resources): MutableList<GS> {
    val assetManager = resources.assets
    val inputStream = assetManager.open("goods_info.json")
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val str: String = bufferedReader.readText()
    val listType = object : TypeToken<MutableList<Goods>>() {}.type
    var array = Gson().fromJson<MutableList<Goods>>(str, listType)

    val inputStream2 = assetManager.open("shop_info.json")
    val bufferedReader2 = BufferedReader(InputStreamReader(inputStream2))
    val str2: String = bufferedReader2.readText()
    val listType2 = object : TypeToken<MutableList<Shops>>() {}.type
    var array2 = Gson().fromJson<MutableList<Shops>>(str2, listType2)
    var array4 = mutableListOf<GS>()
    for (i in array.indices) {
        var array3 = GS(array[i].goods_name,array2[0].shop_name, array[i].imageName)
           array4.add(array3)
        }
    return array4
}