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

        fun getNavPage(): Int {
            try {
                return preferencesManager.getNavPage()
            } catch (e: Exception) {
                return 0
            }
        }

        fun steptToNextPage() {
            try {
                preferencesManager.steptToNextPage()
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
            }
        }

        fun steptToPreviousPage() {
            try {
                preferencesManager.steptToPreviousPage()
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
            }
        }

        fun storeNavPage(page: Int) {
            try {
                preferencesManager.storeNavPage(page)
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
            }
        }

        fun deleteStoredMessage(): String =
            try {
                preferencesManager.deleteStoredMessage()
                ""
            } catch (e: Exception) {
                Log.e("Error", e.message.orEmpty())
                ""
            }
    }
