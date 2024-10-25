package ar.edu.unlam.mobile.scaffolding.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class LocalDataManagerImplementation
    @Inject
    constructor(
        private val sharedPreferences: SharedPreferences,
    ) : LocalDataManager {
        private val loginToken: String = "login_token"

        override fun saveLoginData(token: String) {
            sharedPreferences.edit().putString(loginToken, token).apply()
        }

        override fun getLoginData(): String? = sharedPreferences.getString(loginToken, null)
    }
