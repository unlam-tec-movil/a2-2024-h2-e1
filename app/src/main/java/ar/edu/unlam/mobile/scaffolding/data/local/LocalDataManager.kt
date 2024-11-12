package ar.edu.unlam.mobile.scaffolding.data.local

interface LocalDataManager {
    fun saveLoginData(token: String)

    fun getLoginData(): String?

    fun saveLastMessage(tuit: String): Unit

    fun getLastMessage(): String?

    // pagination
    fun getNavPage(): Int

    fun steptToNextPage()

    fun steptToPreviousPage()

    fun storeNavPage(page: Int)

    fun deleteStoredMessage()
}
