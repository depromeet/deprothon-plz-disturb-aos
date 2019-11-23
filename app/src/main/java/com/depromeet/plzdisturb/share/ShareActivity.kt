package com.depromeet.plzdisturb.share

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.depromeet.plzdisturb.R
import kotlinx.android.synthetic.main.activity_share.*


class ShareActivity : AppCompatActivity() {

    private var code = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        initView()
        // intent 에서 코드 받아와서 저장
        code = "ABCDEFG"

        handleDeepLink()
    }

    private fun initView() {
        btn_code_share.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "https://lee-kyungseok.github.io/temp/index.html?code=$code")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, "Share"))
        }
    }

    private fun handleDeepLink() {
        val uriData = intent?.data
        uriData?.getQueryParameter("code")?.let {

        }
    }

    companion object {
        private const val TAG = "ShareActivity"

        const val ROOM_SEGMENT = "room"
        const val KEY_ROOM_CODE = "code"
    }
}
