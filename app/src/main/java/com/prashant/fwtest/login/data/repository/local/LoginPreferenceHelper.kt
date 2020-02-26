package com.prashant.fwtest.login.data.repository.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
private val PREF_FILE_NAME = "FW_PREF"
private val BEARER = "Bearer "

@Singleton
class LoginPreferencesHelper @Inject constructor(context: Context) {

    private var mPrefs: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }


    fun getAccessToken() = BEARER+ mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)

    fun isUserLoggedIn() = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null).isNullOrEmpty().not()

    fun setAccessToken(accessToken: String) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    fun clearAccessToken() {
        mPrefs.edit().remove(PREF_KEY_ACCESS_TOKEN).apply()
    }
}