package com.example.myscheduler

import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration
import org.bson.types.ObjectId

class MyMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val realmSchema = realm.schema // Realmのスキーマ
        var oldVersion = oldVersion

        if (oldVersion == 0L) { // スキーマのバージョンが以前のものであるとき
            //realmSchema.create("Task") // NewModelを新規作成した
             //   .addField("_id", ObjectId::class.java, FieldAttribute.PRIMARY_KEY) // @PrimaryKeyアノテーション付きのプロパティ
             //   .addField("_partition", String::class.java)
            //    .addField("name", String::class.java)
            //realmSchema.get("Schedule")!! //  ExistModelにプロパティを追加した
            //    .removeField("_id")
           //     .addField("scheduleId", Long::class.java, FieldAttribute.PRIMARY_KEY)
           //     .renameField("_id","scheduleId")
            //    .removeField("scheduleId")// nullを許容しない場合、REQUIREDが必要らしい（ソースがない……）
           //
           //     .addField("statusEnum", TaskStatus::class.java, FieldAttribute.REQUIRED)
            oldVersion++ // マイグレーションしたためスキーマバージョンを上げる
        }
    }
}