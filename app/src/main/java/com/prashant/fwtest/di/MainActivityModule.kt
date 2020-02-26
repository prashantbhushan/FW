package com.elifox.legocatalog.di

import com.prashant.fwtest.article.ui.detail.ArticleDetailActivity
import com.prashant.fwtest.article.ui.list.ArticleListActivity
import com.prashant.fwtest.login.ui.LoginActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeArticleListActivity(): ArticleListActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeArticleDetailActivity(): ArticleDetailActivity
}
