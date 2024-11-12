package ar.edu.unlam.mobile.scaffolding.domain.pagination.service

interface PaginationManagerInterface {
    fun initSaveData(): Unit

    fun goNextPage(): Unit

    fun getPage(): Int

    fun goPreviousPage(): Unit
}
