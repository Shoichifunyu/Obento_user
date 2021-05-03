package com.example.myscheduler

import android.app.Application
import android.util.Log

import io.realm.Realm
import io.realm.log.LogLevel
import io.realm.log.RealmLog
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration

//Realmアプリケーションのインスタンス（グローバルインスタンスとして、アプリケーション全体で共有する）
lateinit var taskApp: App

// global Kotlin extension that resolves to the short version
// of the name of the current class. Used for labelling logs.
inline fun <reified T> T.TAG(): String = T::class.java.simpleName

/*
* TaskTracker: Sets up the taskApp Realm App and enables Realm-specific logging in debug mode.
*/
class TaskTracker : Application() {

    override fun onCreate() {
        super.onCreate()
        //Realmライブラリの初期化
        Realm.init(this)
        //Realmアプリケーションにアクセスしインスタンス化
        taskApp = App(
            AppConfiguration.Builder(BuildConfig.MONGODB_REALM_APP_ID)
                .build())

        // デバッグモード時に追加ロギングを有効に
        if (BuildConfig.DEBUG) {
            RealmLog.setLevel(LogLevel.ALL)
        }

        Log.v(TAG(), "Initialized the Realm App configuration for: ${taskApp.configuration.appId}")
    }
}