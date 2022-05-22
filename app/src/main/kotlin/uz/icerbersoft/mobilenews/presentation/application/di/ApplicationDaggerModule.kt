package uz.icerbersoft.mobilenews.presentation.application.di

import dagger.Module
import uz.icerbersoft.mobilenews.presentation.application.di.data.DataDaggerModuleDao
import uz.icerbersoft.mobilenews.presentation.application.di.data.DataDaggerModulePreference
import uz.icerbersoft.mobilenews.presentation.application.di.data.DataDaggerModuleRepository
import uz.icerbersoft.mobilenews.presentation.application.di.data.DataDaggerModuleRest
import uz.icerbersoft.mobilenews.presentation.application.di.domain.DomainDaggerModuleUseCase

@Module(
    includes = [
        ApplicationDaggerModuleManager::class,
        ApplicationDaggerModuleSubComponents::class,
        DataDaggerModuleDao::class,
        DataDaggerModulePreference::class,
        DataDaggerModuleRepository::class,
        DataDaggerModuleRest::class,
        DomainDaggerModuleUseCase::class
    ]
)
object ApplicationDaggerModule