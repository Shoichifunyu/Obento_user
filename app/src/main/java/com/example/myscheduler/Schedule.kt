package com.example.myscheduler

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId
import java.util.*

open class Schedule: RealmObject() {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    @Index
    var scheduleId: Long = 0
    var date: Date = Date()
    var personname: String = ""
    var detail: String = ""
}