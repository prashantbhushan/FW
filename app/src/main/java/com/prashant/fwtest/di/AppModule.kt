package com.elifox.legocatalog.di

import com.prashant.fwtest.article.data.repository.ArticleRepository
import com.prashant.fwtest.article.data.repository.remote.ArticleRemoteDataSource
import com.prashant.fwtest.di.NetworkModule
import com.prashant.fwtest.di.scheduler.RunOn
import com.prashant.fwtest.di.scheduler.SchedulerType
import com.prashant.fwtest.login.data.repository.LoginRepository
import com.prashant.fwtest.login.data.repository.local.LoginPreferencesHelper
import com.prashant.fwtest.login.data.repository.remote.LoginRemoteDataSource
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, NetworkModule::class])
class AppModule {

    @Provides
    @Singleton
    fun providesLoginRepository(
        remoteDataSource: LoginRemoteDataSource,
        prefHelper: LoginPreferencesHelper
    ): LoginRepository {
        return LoginRepository(remoteDataSource, prefHelper)
    }

    @Provides
    @Singleton
    fun providesArticleRepository(
        remoteDataSource: ArticleRemoteDataSource
    ): ArticleRepository {
        return ArticleRepository(remoteDataSource)
    }

    @Provides
    @RunOn(SchedulerType.IO)
    fun provideIO() = Schedulers.io()

    @Provides
    @RunOn(SchedulerType.UI)
    fun provideUI() = AndroidSchedulers.mainThread()
}
