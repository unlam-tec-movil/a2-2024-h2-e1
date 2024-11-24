package ar.edu.unlam.mobile.scaffolding.data.local.localdata.services

import android.content.SharedPreferences
import ar.edu.unlam.mobile.scaffolding.data.local.localdata.interfaces.LastMessageManager
import javax.inject.Inject

class LastMessageManagerImplementation
    @Inject
    constructor(
        private val sharedPreferences: SharedPreferences,
    ) : LastMessageManager {
        private val lastMessage: String = "last_message"

        override fun saveLastMessage(tuit: String) = sharedPreferences.edit().putString(lastMessage, tuit).apply()

        override fun getLastMessage(): String? = sharedPreferences.getString(lastMessage, null)

        override fun deleteStoredMessage() {
            sharedPreferences.edit().putString("", "").apply()
        }
    }
