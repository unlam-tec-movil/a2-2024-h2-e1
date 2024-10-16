package ar.edu.unlam.mobile.scaffolding.data.di

import ar.edu.unlam.mobile.scaffolding.data.network.TuitRepositoryImplementation
import ar.edu.unlam.mobile.scaffolding.domain.tuit.repository.TuitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class TuitFeedModule {
    @Binds
    abstract fun bindTuitRepository(tuitRepositoryimpl: TuitRepositoryImplementation): TuitRepository
}
