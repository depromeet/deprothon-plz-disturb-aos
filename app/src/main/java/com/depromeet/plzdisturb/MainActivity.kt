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
        }
    }

}
