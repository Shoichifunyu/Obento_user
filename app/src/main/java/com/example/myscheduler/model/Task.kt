package com.example.myscheduler.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId

//"tasks"コレクションに相当するデータモデルを定義するクラス(projectの初期値は適宜修正)
open class Task(_name: String = "Task", project: String? = "via_android_studio") : RealmObject() {
    //コレクションに存在するプロパティ(フィールド)の定義と、初期値の入力
    @PrimaryKey var _id: ObjectId = ObjectId()
    var _partition: String? = project
    var name: String = _name

    //statusプロパティの定義
    @Required
    private var status: String = TaskStatus.Open.name
    var statusEnum: TaskStatus
        get() {
            // because status is actually a String and another client could assign an invalid value,
            // default the status to "Open" if the status is unreadable
            return try {
                TaskStatus.valueOf(status)
            } catch (e: IllegalArgumentException) {
                TaskStatus.Open
            }
        }
        set(value) { status = value.name }
}