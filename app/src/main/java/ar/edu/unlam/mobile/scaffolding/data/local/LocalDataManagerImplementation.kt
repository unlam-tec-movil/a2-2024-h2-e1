package ar.edu.unlam.mobile.scaffolding.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class LocalDataManagerImplementation
    @Inject
    constructor(
        private val sharedPreferences: SharedPreferences,
    ) : LocalDataManager {
        private val loginToken: String = "login_token"
        private val lastMessage: String = "last_message"

        override fun saveLoginData(token: String) {
            sharedPreferences.edit().putString(loginToken, token).apply()
        }

        override fun getLoginData(): String? = sharedPreferences.getString(loginToken, null)

        override fun saveLastMessage(tuit: String) = sharedPreferences.edit().putString(lastMessage, tuit).apply()

        override fun getLastMessage(): String? = sharedPreferences.getString(lastMessage, null)
    }
