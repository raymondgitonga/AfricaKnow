package com.tosh.africaknow.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

fun addToSharedPreferences(context: Context, num: Int) {
    var preferences: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(context)
    var editor: SharedPreferences.Editor?

    editor = preferences?.edit()
    editor?.putInt("NUM", num)
    editor?.apply()

}

fun addScorePreferences(context: Context, score: Int) {
    var preferences: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(context)
    var editor: SharedPreferences.Editor?

    editor = preferences?.edit()
    editor?.putInt("SCORE", score)
    editor?.apply()

}

fun getSharedPreferencesValue(context: Context, key: String?): Int{
    val mSharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
    return mSharedPreference.getInt(key,0)
}