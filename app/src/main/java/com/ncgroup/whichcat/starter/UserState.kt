package com.ncgroup.whichcat.starter

import android.content.Context
import com.ncgroup.whichcat.WhichCatsApplication


private val sharedPref = WhichCatsApplication.INSTANCE.getSharedPreferences("user_state", Context.MODE_PRIVATE)

fun getUserState(): Boolean {
    return sharedPref.getBoolean("isStarted", false)
}

fun setUserState(isStarted: Boolean){
    with(sharedPref.edit()){
        putBoolean("isStarted", isStarted)
        apply()
    }
}