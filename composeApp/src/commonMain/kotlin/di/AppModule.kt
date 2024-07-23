package di

import network.APIClient
import data.MyRepository
import domain.Repository
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import screen.HomeScreenViewModel

val appModule = module {
    single<Repository> { MyRepository(APIClient()) }
    viewModel { HomeScreenViewModel(get()) }

}

fun initializeKoin(){
    startKoin {
        modules(appModule)
    }
}