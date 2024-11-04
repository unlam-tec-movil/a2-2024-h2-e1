package ar.edu.unlam.mobile.scaffolding.data.local.repository

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.local.LocalDataManager
import javax.inject.Inject

class LocalDataRepository
    @Inject
    constructor(
        private val preferencesManager: LocalDataManager,
    ) {
        fun saveLoginToken(token: String) =
            try {
                preferencesManager.saveLoginData(token)
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
                e.printStackTrace()
            }

        fun getLoginToken(): String? = preferencesManager.getLoginData()

        fun storeLastMessage(tuit: String) =
            try {
                preferencesManager.saveLastMessage(tuit)
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
            }

        fun getLastMessage(): String {
            try {
                return preferencesManager.getLastMessage().orEmpty()
            } catch (e: Exception) {
                return ""
            }
        }
    }
