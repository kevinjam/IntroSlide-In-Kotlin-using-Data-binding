package com.kevinjanvier.introkotlin.slide


import android.content.Context
import android.content.SharedPreferences

/**
 * Created by kevinjanvier on 10/17/16.
 */

class Constants(internal var _context: Context) {
    internal var pref: SharedPreferences
    internal var editor: SharedPreferences.Editor

    // shared pref mode
    internal var PRIVATE_MODE = 0

    // If the user has not chosen a city yet, return
    // Sydney as the default city
    //return prefs.getString("city", "Jerusalem, IS");
    //return prefs.getString("city", "Sydney, AU");
    var city: String
        get() = pref.getString("city", "Jerusalem, IL")
        set(city) {
            pref.edit().putString("city", city).commit()
        }

    var isFirstTimeLaunch: Boolean
        get() = pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
            editor.commit()
        }

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object {

        //


        // Shared preferences file name
        private val PREF_NAME = "Kuangalia-Welcome"
        val BARCODE_KEY = "BARCODE"
        private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
        val BASE_URL = "http://latterglory.ug/api/"
        val SPLASH_TIME = 3000
        val TITLE = "title"
        val DESCRIPTION = "description"
        val KEY_IMAGE = "news_image"
        val OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric"
    }

}
