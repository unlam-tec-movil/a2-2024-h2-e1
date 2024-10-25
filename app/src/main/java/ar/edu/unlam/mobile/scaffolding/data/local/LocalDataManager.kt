package ar.edu.unlam.mobile.scaffolding.data.local

interface LocalDataManager {
    fun saveLoginData(token: String)

    fun getLoginData(): String?
}
