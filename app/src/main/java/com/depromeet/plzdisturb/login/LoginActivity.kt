package com.depromeet.plzdisturb.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.depromeet.plzdisturb.R
import com.depromeet.plzdisturb.data.dto.LoginRequest
import com.depromeet.plzdisturb.data.dto.LoginResponse
import com.depromeet.plzdisturb.makeroom.MakeRoomActivity
import com.depromeet.plzdisturb.network.DisturbingService
import com.depromeet.plzdisturb.updateAccessToken
import com.google.firebase.iid.FirebaseInstanceId
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private inner class SessionCallback : ISessionCallback {

        override fun onSessionOpened() {
            // 성공시 작업
            val accessToken = Session.getCurrentSession().tokenInfo.accessToken
            Log.e(TAG, accessToken)

            // token 을 가지고 로그인 시킨다.
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.e("getToken", "getInstanceId failed", task.exception)
                        return@addOnCompleteListener
                    }

                    // Get new Instance ID token
                    val deviceToken = task.result?.token

                    // Log and toast
                    Log.e(TAG, "InstanceID Token: $deviceToken")

                    DisturbingService.getApi(this@LoginActivity).postLogin(LoginRequest(accessToken, deviceToken))
                        .enqueue(object : Callback<LoginResponse> {
                            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                // Todo 실패 안할꺼야!!!
                            }

                            override fun onResponse(
                                call: Call<LoginResponse>,
                                response: Response<LoginResponse>
                            ) {

                                Log.e(TAG, "asdfsadf ${response}")
                                response.body()?.let {
                                    Log.e(TAG, it.accesstoken)
                                    updateAccessToken(it.accesstoken, this@LoginActivity)
                                    startActivity(Intent(this@LoginActivity, MakeRoomActivity::class.java))
                                }
                            }

                        })

                }
        }

        override fun onSessionOpenFailed(exception: KakaoException?) {
            Toast.makeText(this@LoginActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT)
        }
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}
