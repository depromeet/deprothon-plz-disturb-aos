package com.depromeet.plzdisturb.room

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.depromeet.plzdisturb.R
import com.depromeet.plzdisturb.data.DisturbingRepositories
import com.depromeet.plzdisturb.disturbing.DisturbingActivity
import com.depromeet.plzdisturb.model.Room
import com.depromeet.plzdisturb.model.User
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity(), RoomContract.View {

    private lateinit var code: String
    var name: String = "펭수"
    var profileUrl: String =
        "https://pds.joins.com/news/component/htmlphoto_mmdata/201910/26/ce877ed2-0800-457f-b9a6-a86044718d40.jpg"

    lateinit var room: Room

    lateinit var adapter: MemberAdapter
    lateinit var presenter: RoomContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val toolbar = findViewById<Toolbar>(R.id.a_room_toolbar)
        setSupportActionBar(toolbar)

        handleDeepLink()

        val actionBar = layoutInflater.inflate(R.layout.actionbar, toolbar)

        presenter = RoomPresenter(DisturbingRepositories.getRepository(), this, code)

        adapter = MemberAdapter()

        a_room_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        a_room_rv.adapter = adapter

        a_room_start_btn.setOnClickListener {
            presenter.start()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.load()
    }

    override fun isActive(): Boolean {
        return !isFinishing
    }

    override fun addUserList(userList: ArrayList<User>?) {
        adapter.setMembers(userList)
    }

    override fun addUser(user: User?) {
        // @TODO : (jonghyo) user 추가 구현 필요
    }

    override fun removeUser(userId: Int) {
        // @TODO : (jonghyo) user 제거 구현 필요
    }

    override fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun goDisturbingScreen() {
        val intent = Intent(this, DisturbingActivity::class.java)
        intent.putExtra("members", adapter.memberList)
        startActivity(intent)
    }

    fun invite() {
        presenter.invite()
    }

    private fun handleDeepLink() {
        val uriData = intent?.data
        uriData?.getQueryParameter("code")?.let {
            this.code = it
        }

        val roomCode = intent?.getStringExtra("code")
        if (roomCode == null) {
            return
        }

        this.code = roomCode
    }

    override fun showShareIntent(link: String?) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "https://lee-kyungseok.github.io/temp/index.html?code=$code")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Share"))
    }

}
