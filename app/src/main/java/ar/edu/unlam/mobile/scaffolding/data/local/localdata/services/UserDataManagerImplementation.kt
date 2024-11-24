package ar.edu.unlam.mobile.scaffolding.data.local.localdata.services

import android.content.SharedPreferences
import ar.edu.unlam.mobile.scaffolding.data.local.localdata.interfaces.UserDataManager
import javax.inject.Inject

class UserDataManagerImplementation
    @Inject
    constructor(
        private val sharedPreferences: SharedPreferences,
    ) : UserDataManager {
        private val loginToken: String = "login_token"

        override fun saveLoginData(token: String) {
            sharedPreferences.edit().putString(loginToken, token).apply()
        }

        override fun getLoginData(): String? = sharedPreferences.getString(loginToken, null)
    }
