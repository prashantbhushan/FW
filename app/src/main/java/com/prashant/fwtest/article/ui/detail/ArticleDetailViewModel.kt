package com.prashant.fwtest.article.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prashant.fwtest.article.data.repository.ArticleRepository
import com.prashant.fwtest.article.domain.ArticleDetailItem
import com.prashant.fwtest.article.domain.toDomain
import com.prashant.fwtest.di.scheduler.RunOn
import com.prashant.fwtest.di.scheduler.SchedulerType
import com.prashant.fwtest.util.Result
import io.reactivex.Scheduler
import javax.inject.Inject

class ArticleDetailViewModel @Inject constructor(
    val repository: ArticleRepository,
    @RunOn(SchedulerType.IO) private val ioScheduler: Scheduler,
    @RunOn(SchedulerType.UI) private val uiScheduler: Scheduler
) : ViewModel() {

    private var _article = MutableLiveData<Result<ArticleDetailItem>>()
    val article: LiveData<Result<ArticleDetailItem>> = _article

    fun loadArticleDetail(articleId: Int) {
        repository.loadArticle(articleId)
            .observeOn(uiScheduler)
            .subscribeOn(ioScheduler)
            .doOnSubscribe({
                _article.value = Result.loading()
            })
            .subscribe({
                _article.value = Result.success(it.toDomain())
            }, {
                _article.value = Result.error(it)
            })
    }
}
