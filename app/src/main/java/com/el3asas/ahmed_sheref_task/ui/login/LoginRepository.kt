package com.el3asas.ahmed_sheref_task.ui.login

import com.el3asas.ahmed_sheref_task.data.local.Pref
import javax.inject.Inject

class LoginRepository @Inject constructor(private val pref: Pref) {

    private var isUserLogin: Boolean = false
        get() = pref.isUserLogin
        set(value) {
            field = value
            pref.isUserLogin = value
        }

    private var userName: String = ""
        get() = pref.userName
        set(value) {
            field = value
            pref.userName = value
        }

    fun login(userName: String, password: String, onSuccess: () -> Unit) {
        isUserLogin = true
        this.userName = userName
        onSuccess()
    }
}