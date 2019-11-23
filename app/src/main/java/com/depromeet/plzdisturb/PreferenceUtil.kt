package com.depromeet.plzdisturb

import android.content.Context
import androidx.preference.PreferenceManager

const val KEY_AUTH_TOKEN = "KEY_AUTH"

fun getAccessToken(context: Context): String? =
    PreferenceManager.getDefaultSharedPreferences(context)
        .getString(KEY_AUTH_TOKEN, null)


fun updateAccessToken(token: String, context: Context): Boolean {
    return PreferenceManager.getDefaultSharedPreferences(context)
        .edit()
        .putString(KEY_AUTH_TOKEN, token)
        .commit()
}