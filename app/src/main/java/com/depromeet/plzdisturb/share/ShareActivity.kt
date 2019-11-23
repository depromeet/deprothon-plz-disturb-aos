package com.depromeet.plzdisturb.share

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.depromeet.plzdisturb.MainActivity
import com.depromeet.plzdisturb.R
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import kotlinx.android.synthetic.main.activity_share.*


class ShareActivity : AppCompatActivity() {

    private var code = "123123123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        initView()


        handleDeepLink()
    }

    private fun initView() {
        btn_share.setOnClickListener {
            onDynamicLinkClick()
        }
    }

    private fun getDeepLink(): Uri =
        Uri.parse("https://plzdisturb.depromeet.com/$ROOM_SEGMENT?$KEY_ROOM_CODE=$code")

    private fun onDynamicLinkClick() {
        val dlink = FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(getDeepLink())
            .setDomainUriPrefix("https://plzdisturb.page.link")
            .setAndroidParameters(
                DynamicLink.AndroidParameters.Builder(packageName)
                    .setMinimumVersion(125)
                    .build())
            .buildShortDynamicLink()
            .addOnFailureListener {
                Log.e(TAG, "딥링크 만들어지지 않음" + it.message)
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val shortLink = task.result?.shortLink
                    try {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, shortLink.toString())
                            type = "text/plain"
                        }
                        startActivity(Intent.createChooser(sendIntent, "Share"))
                    } catch (e: ActivityNotFoundException) {
                        Log.e(TAG, "ActivityNotFoundException")
                    }
                } else {
//                    Log.e(TAG, "딥링크 만들어지지 않음")
                }
            }
    }

    private fun handleDeepLink() {
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(this, OnSuccessListener { pendingDynamicLinkData ->
                if (pendingDynamicLinkData == null) {
                    Log.d(TAG, "No have dynamic link")
                    return@OnSuccessListener
                }
                val deepLink = pendingDynamicLinkData.link
                Log.d(TAG, "deepLink: " + deepLink!!)

                when (deepLink.lastPathSegment) {
                    ROOM_SEGMENT -> {
                        val code = deepLink.getQueryParameter(KEY_ROOM_CODE)
                        Log.e(TAG, "code is $code")
                    }
                }
            })
            .addOnFailureListener(this) { e ->
                Log.w(TAG, "getDynamicLink:onFailure", e)
            }
    }

    companion object {
        private const val TAG = "ShareActivity"

        const val ROOM_SEGMENT = "room"
        const val KEY_ROOM_CODE = "code"
    }
}
