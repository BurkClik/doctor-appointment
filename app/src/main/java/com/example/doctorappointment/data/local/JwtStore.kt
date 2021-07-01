package com.example.doctorappointment.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class JwtStore @Inject constructor(@ApplicationContext context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_FILE_NAME, MODE_PRIVATE)

    fun saveMail(mail: String) = prefs.edit(commit = true) {
        putString(PREFS_KEY_MAIL, mail)
    }

    fun loadMail(): String? = prefs.getString(PREFS_KEY_MAIL, null)

    fun deleteMail() = prefs.edit(commit = true) {
        remove(PREFS_KEY_MAIL)
    }

    fun saveJwt(jwt: String) = prefs.edit(commit = true) {
        putString(PREFS_KEY_JWT, jwt)
    }

    fun loadJwt(): String? = prefs.getString(PREFS_KEY_JWT, null)

    fun deleteJwt() = prefs.edit(commit = true) {
        remove(PREFS_KEY_JWT)
    }

    fun saveName(name: String) = prefs.edit(commit = true) {
        putString(PREFS_KEY_NAME, name)
    }

    fun loadName(): String? = prefs.getString(PREFS_KEY_NAME, null)

    fun deleteName() = prefs.edit(commit = true) {
        remove(PREFS_KEY_NAME)
    }

    fun saveId(id: String) = prefs.edit(commit = true) {
        putString(PREFS_KEY_ID, id)
    }

    fun loadId(): String? = prefs.getString(PREFS_KEY_ID, null)

    fun deleteId() = prefs.edit(commit = true) {
        remove(PREFS_KEY_ID)
    }

    companion object {
        const val PREFS_FILE_NAME = "jwt_store"
        const val PREFS_KEY_JWT = "jwt"
        const val PREFS_KEY_MAIL = "mail"
        const val PREFS_KEY_NAME = "name"
        const val PREFS_KEY_ID = "id"
    }
}