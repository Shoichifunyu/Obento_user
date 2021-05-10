package com.example.myscheduler

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId
import java.util.*

open class Schedule(project: String? = "via_android_studio"): RealmObject() {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var _partition: String? = project
    @Required
    var date: Date = Date()
    var personname: String = ""
    var detail: String = ""
}