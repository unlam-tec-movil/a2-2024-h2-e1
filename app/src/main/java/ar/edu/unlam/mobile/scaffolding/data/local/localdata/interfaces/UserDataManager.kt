package ar.edu.unlam.mobile.scaffolding.data.local.localdata.interfaces

interface UserDataManager {
    fun saveLoginData(token: String)

    fun getLoginData(): String?
}
