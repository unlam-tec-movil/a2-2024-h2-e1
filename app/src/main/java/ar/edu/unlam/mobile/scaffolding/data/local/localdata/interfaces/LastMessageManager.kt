package ar.edu.unlam.mobile.scaffolding.data.local.localdata.interfaces

interface LastMessageManager {
    fun saveLastMessage(tuit: String): Unit

    fun getLastMessage(): String?

    fun deleteStoredMessage()
}
