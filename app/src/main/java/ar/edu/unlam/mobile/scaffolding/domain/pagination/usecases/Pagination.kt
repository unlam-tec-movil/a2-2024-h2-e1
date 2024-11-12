package ar.edu.unlam.mobile.scaffolding.domain.pagination.usecases

import ar.edu.unlam.mobile.scaffolding.data.local.repository.LocalDataRepository
import ar.edu.unlam.mobile.scaffolding.domain.pagination.service.PaginationManagerInterface
import javax.inject.Inject

class Pagination
    @Inject
    constructor(
        private val localData: LocalDataRepository,
    ) : PaginationManagerInterface {
        override fun initSaveData() = localData.storeNavPage(1)

        override fun goNextPage() = localData.steptToNextPage()

        override fun goPreviousPage() = localData.steptToPreviousPage()

        override fun getPage(): Int = localData.getNavPage()
    }
