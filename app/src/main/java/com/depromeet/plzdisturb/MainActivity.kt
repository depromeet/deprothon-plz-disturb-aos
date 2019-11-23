package com.depromeet.plzdisturb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import com.depromeet.plzdisturb.disturbing.DisturbingActivity
import com.depromeet.plzdisturb.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var id: Int = 0
    var name: String = "펭수"
    var profileUrl: String =
        "https://pds.joins.com/news/component/htmlphoto_mmdata/201910/26/ce877ed2-0800-457f-b9a6-a86044718d40.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add.setOnClickListener {
            var list = ArrayList<User>()
            list.add(User(0, name + 0, profileUrl))
            list.add(User(1, name + 1, profileUrl))
            list.add(User(2, name + 2, profileUrl))
            list.add(User(3, name + 3, profileUrl))
            list.add(User(4, name + 4, profileUrl))
            list.add(User(5, name + 5, profileUrl))
            list.add(User(6, name + 6, profileUrl))
            list.add(User(7, name + 7, profileUrl))
            list.add(User(8, name + 8, profileUrl))
            list.add(User(9, name + 9, profileUrl))
            val intent = Intent(this, DisturbingActivity::class.java)
            intent.putExtra("members", list)
            startActivity(intent)
        }
    }

}
