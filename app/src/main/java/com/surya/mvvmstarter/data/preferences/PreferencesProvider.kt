package com.surya.mvvmstarter.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by suryamudti on 04/08/2019.
 */

private const val KEY_SAVED_AT = "key_saved_at"

class PreferencesProvider(
    context: Context) {

    private val appContext = context.applicationContext

    private val preferences : SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastSavedAt(savedAt: String){
        preferences.edit().putString(
            KEY_SAVED_AT,
            savedAt
        ).apply()
    }

    fun getLastSavedAt() : String? {
        return preferences.getString(KEY_SAVED_AT,null)
    }

}