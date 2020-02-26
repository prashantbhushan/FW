package com.prashant.fwtest.article.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.prashant.fwtest.R
import com.prashant.fwtest.article.domain.ArticleDetailItem
import com.prashant.fwtest.util.BaseActivity
import com.prashant.fwtest.util.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_article_detail.*
import javax.inject.Inject


const val ARTICLE_ID = "articleId"

class ArticleDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ArticleDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        setUpToolbar()
        setUpViewModel()
        viewModel.loadArticleDetail(intent.getIntExtra(ARTICLE_ID, -1))
    }

    private fun setUpToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[ArticleDetailViewModel::class.java]

        viewModel.article.observe(this, Observer {
            when (it?.status) {
                Result.SUCCESS -> {
                    hideLoadingIndicator()
                    handleArticleDetailResult(it.data)
                }
                Result.LOADING -> showLoadingIndicator()
                Result.ERROR -> {
                    hideLoadingIndicator()
                    showArticleDetailError(it.error)
                }
            }
        })
    }

    private fun showArticleDetailError(t: Throwable?) {
        t?.let { Snackbar.make(container, it.localizedMessage, Snackbar.LENGTH_LONG).show() }
    }

    private fun handleArticleDetailResult(data: ArticleDetailItem?) {
        data?.let {
            setTitle(data.title)
            Picasso.get()
                .load(data.imageUrl)
                .fit()
                .centerCrop()
                .into(ivArticleDetail)
            tvTitle.text = data.title
            tvDate.text = data.date
            tvContent.text = data.content
        }
    }

    private fun showLoadingIndicator() {
        pbArticleDetail.visibility = View.VISIBLE
    }

    private fun hideLoadingIndicator() {
        pbArticleDetail.visibility = View.GONE
    }

    private fun setTitle(title: String) {
        getSupportActionBar()?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        fun getIntent(context: Context, articleId: Int) =
            Intent(context, ArticleDetailActivity::class.java).apply {
                putExtra(ARTICLE_ID, articleId)
            }
    }
}
