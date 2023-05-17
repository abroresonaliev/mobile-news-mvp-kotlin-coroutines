package uz.icebergsoft.mobilenews.presentation.application.di

import dagger.Module
import uz.icebergsoft.mobilenews.presentation.application.di.data.DataDaggerModuleDao
import uz.icebergsoft.mobilenews.presentation.application.di.data.DataDaggerModulePreference
import uz.icebergsoft.mobilenews.presentation.application.di.data.DataDaggerModuleRepository
import uz.icebergsoft.mobilenews.presentation.application.di.data.DataDaggerModuleNetwork
import uz.icebergsoft.mobilenews.presentation.application.di.domain.DomainDaggerModuleUseCase

@Module(
    includes = [
        ApplicationDaggerModuleManager::class,
        ApplicationDaggerModuleSubComponents::class,
        DataDaggerModuleDao::class,
        DataDaggerModulePreference::class,
        DataDaggerModuleRepository::class,
        DataDaggerModuleNetwork::class,
        DomainDaggerModuleUseCase::class
    ]
)
object ApplicationDaggerModule