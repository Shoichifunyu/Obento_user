package com.example.myscheduler.model

import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myscheduler.R//パッケージ名に合わせて修正
import com.example.myscheduler.TAG//パッケージ名に合わせて修正
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmRecyclerViewAdapter
import io.realm.kotlin.where
import org.bson.types.ObjectId

/*
* Taskコレクションデータ表示用クラス(Realm取得データをRecyclerViewに表示するためのクラスRealmRecyclerViewAdapterを継承)
* TaskAdapter: extends the Realm-provided RealmRecyclerViewAdapter to provide data for a RecyclerView to display
* Realm objects on screen to a user.
*/
internal class TaskAdapter(data: OrderedRealmCollection<Task>) : RealmRecyclerViewAdapter<Task, TaskAdapter.TaskViewHolder?>(data, true) {

    //xmlレイアウトからViewを生成して、含まれるView一覧をTaskViewHolder形式で返す
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        //task_view.xmlから全体Viewを生成
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.task_view, parent, false)
        //全体Viewに含まれるView一覧を返す
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val obj: Task? = getItem(position)
        holder.data = obj
        holder.name.text = obj?.name
        holder.status.text = obj?.statusEnum?.displayName

        //手動でステータス変更 or タスク削除するためのポップアップ処理
        // multiselect popup to control status
        holder.itemView.setOnClickListener {
            run {
                val popup = PopupMenu(holder.itemView.context, holder.menu)
                val menu = popup.menu

                //ステータス変更をポップアップメニューに追加（現在のステータスは除外）
                // the menu should only contain statuses different from the current status
                if (holder.data?.statusEnum != TaskStatus.Open) {
                    menu.add(0, TaskStatus.Open.ordinal, Menu.NONE, TaskStatus.Open.displayName)
                }
                if (holder.data?.statusEnum != TaskStatus.InProgress) {
                    menu.add(0, TaskStatus.InProgress.ordinal, Menu.NONE, TaskStatus.InProgress.displayName)
                }
                if (holder.data?.statusEnum != TaskStatus.Complete) {
                    menu.add(0, TaskStatus.Complete.ordinal, Menu.NONE, TaskStatus.Complete.displayName)
                }

                //タスク削除をポップアップメニューに追加
                // add a delete button to the menu, identified by the delete code
                val deleteCode = -1
                menu.add(0, deleteCode, Menu.NONE, "Delete Task")

                //ポップアップメニューのボタンをクリックした際の処理
                // handle clicks for each button based on the code the button passes the listener
                popup.setOnMenuItemClickListener { item: MenuItem? ->
                    var status: TaskStatus? = null
                    when (item!!.itemId) {
                        TaskStatus.Open.ordinal -> {//ステータスを「開始」に変更
                            status = TaskStatus.Open
                        }
                        TaskStatus.InProgress.ordinal -> {//ステータスを「実行中」に変更
                            status = TaskStatus.InProgress
                        }
                        TaskStatus.Complete.ordinal -> {//ステータスを「完了」に変更
                            status = TaskStatus.Complete
                        }
                        deleteCode -> {//タスクを削除
                            removeAt(holder.data?._id!!)
                        }
                    }

                    //ステータスが変更されたとき、変更をRealmに反映させる
                    // if the status variable has a new value, update the status of the task in realm
                    if (status != null) {
                        Log.v(TAG(), "Changing status of ${holder.data?.name} (${holder.data?._id}) to $status")
                        changeStatus(status!!, holder.data?._id)
                    }
                    true
                }
                popup.show()
            }}
    }

    //ステータス変更をRealmに反映
    private fun changeStatus(status: TaskStatus, _id: ObjectId?) {
        //Realmインスタンスを新規作成する
        // need to create a separate instance of realm to issue an update, since this event is
        // handled by a background thread and realm instances cannot be shared across threads
        val bgRealm = Realm.getInstance(RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true)
                .allowQueriesOnUiThread(true)
                .build())
        //トランザクションを同期処理で実行（ステータス変更はバックグラウンドスレッドのみで実行される必要があるため）
        // execute Transaction (not async) because changeStatus should execute on a background thread
        bgRealm!!.executeTransaction {
            //選択したタスクとIDが等しいデータをRealmからクエリで選択し、ステータスを変更
            // using our thread-local new realm instance, query for and update the task status
            val item = it.where<Task>().equalTo("_id", _id).findFirst()
            item?.statusEnum = status
        }
        // always close realms when you are done with them!
        bgRealm.close()
    }

    //タスクの削除
    private fun removeAt(id: ObjectId) {
        // need to create a separate instance of realm to issue an update, since this event is
        // handled by a background thread and realm instances cannot be shared across threads
        val bgRealm = Realm.getInstance(RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true)
                .allowQueriesOnUiThread(true)
                .build())
        // execute Transaction (not async) because remoteAt should execute on a background thread
        bgRealm!!.executeTransaction {
            //選択したタスクとIDが等しいデータをRealmからクエリで選択し、削除
            // using our thread-local new realm instance, query for and delete the task
            val item = it.where<Task>().equalTo("_id", id).findFirst()
            item?.deleteFromRealm()
        }
        // always close realms when you are done with them!
        bgRealm.close()
    }

    //RecyclerViewの中に含まれるViewおよびデータを定義するクラス
    internal inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var status: TextView = view.findViewById(R.id.status)
        var data: Task? = null
        var menu: TextView = view.findViewById(R.id.menu)

    }
}