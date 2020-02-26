package com.prashant.fwtest.article.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prashant.fwtest.article.data.repository.ArticleRepository
import com.prashant.fwtest.article.domain.ArticleListItem
import com.prashant.fwtest.article.domain.toDomain
import com.prashant.fwtest.di.scheduler.RunOn
import com.prashant.fwtest.di.scheduler.SchedulerType
import com.prashant.fwtest.login.data.repository.LoginRepository
import com.prashant.fwtest.util.Result
import io.reactivex.Scheduler
import javax.inject.Inject

class ArticleListViewModel @Inject constructor(
    val loginRepository: LoginRepository,
    val repository: ArticleRepository,
    @RunOn(SchedulerType.IO) private val ioScheduler: Scheduler,
    @RunOn(SchedulerType.UI) private val uiScheduler: Scheduler
) : ViewModel() {

    private var _logout = MutableLiveData<Result<Boolean>>()
    val logout: LiveData<Result<Boolean>> = _logout

    private var _list = MutableLiveData<Result<List<ArticleListItem>>>()
    val list: LiveData<Result<List<ArticleListItem>>> = _list

    fun loadArticlesList() {
        repository.loadArticlesList()
            .observeOn(uiScheduler)
            .subscribeOn(ioScheduler)
            .doOnSubscribe({
                _list.value = Result.loading()
            })
            .subscribe({
                _list.value = Result.success(it.map { it.toDomain() })
            }, {
                _list.value = Result.error(it)
            })
    }

    fun logout() {
        loginRepository.logout()
            .observeOn(uiScheduler)
            .subscribeOn(ioScheduler)
            .doOnSubscribe({
                _logout.value = Result.loading()
            })
            .subscribe({
                _logout.value = Result.success(true)
            }, {
                _logout.value = Result.error(it)
            })
    }
}