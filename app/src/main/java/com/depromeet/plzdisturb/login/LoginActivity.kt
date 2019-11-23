package com.depromeet.plzdisturb.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.depromeet.plzdisturb.R
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private val sessionCallback = SessionCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Session.getCurrentSession().addCallback(sessionCallback)
        Session.getCurrentSession().checkAndImplicitOpen()

        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(sessionCallback)
    }

    private fun initView() {
        Glide.with(this)
            .load(R.drawable.login_background)
            .into(iv_login_background)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            Log.e("asdfasdf", "asdfsadfasfef")
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private inner class SessionCallback : ISessionCallback {

        override fun onSessionOpened() {
            // 성공시 작업
            val token = Session.getCurrentSession().tokenInfo.accessToken

            // token 을 가지고 로그인 시킨다.
            Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_SHORT)
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            Toast.makeText(this@LoginActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT)
        }
    }
}
