package ar.edu.unlam.mobile.scaffolding.ui.di

import ar.edu.unlam.mobile.scaffolding.domain.pagination.service.PaginationManagerInterface
import ar.edu.unlam.mobile.scaffolding.domain.pagination.usecases.Pagination
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalDataServicesModules {
    @Binds
    abstract fun bindPaginationRepository(paginationRepositoryImpl: Pagination): PaginationManagerInterface
}
