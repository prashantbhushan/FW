package com.prashant.fwtest.article.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.prashant.fwtest.R
import com.prashant.fwtest.article.domain.ArticleListItem
import com.prashant.fwtest.article.ui.detail.ArticleDetailActivity
import com.prashant.fwtest.login.ui.LoginActivity
import com.prashant.fwtest.util.BaseActivity
import com.prashant.fwtest.util.Result
import kotlinx.android.synthetic.main.activity_article_list.*
import javax.inject.Inject


class ArticleListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ArticleListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
        setUpToolbar()
        setUpViewModel()
        viewModel.loadArticlesList()
    }

    private fun setUpToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        getSupportActionBar()?.title = getString(R.string.article_list_toolbar)
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[ArticleListViewModel::class.java]

        viewModel.list.observe(this, Observer {
            when (it?.status) {
                Result.SUCCESS -> {
                    hideLoadingIndicator()
                    handleArticlesListResult(it.data)
                }
                Result.LOADING -> showLoadingIndicator()
                Result.ERROR -> {
                    hideLoadingIndicator()
                    showArticlesListError(it.error)
                }
            }
        })

        viewModel.logout.observe(this, Observer {
            when (it?.status) {
                Result.SUCCESS -> {
                    if (it.data == true) {
                        hideLoadingIndicator()
                        launchLoginScreen()
                        finish()
                    }
                }
                Result.LOADING -> showLoadingIndicator()
                Result.ERROR -> {
                    hideLoadingIndicator()
                    showArticlesListError(it.error)
                }
            }
        })
    }

    private fun launchLoginScreen() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun showArticlesListError(t: Throwable?) {
        t?.let { Snackbar.make(container, it.localizedMessage, Snackbar.LENGTH_LONG).show() }
    }

    private fun handleArticlesListResult(data: List<ArticleListItem>?) {
        data?.let {
            with(rvArticleList) {
                addItemDecoration(
                    DividerItemDecoration(
                        applicationContext,
                        DividerItemDecoration.VERTICAL
                    )
                )
                layoutManager = LinearLayoutManager(ArticleListActivity@ this.context)
                adapter = ArticleListAdapter(it) {
                    launchArticleDetailScreen(it)
                }
            }
        }
    }

    private fun launchArticleDetailScreen(id: Int) {
        startActivity(ArticleDetailActivity.getIntent(this, id))
    }

    private fun showLoadingIndicator() {
        pbArticleList.visibility = View.VISIBLE
    }

    private fun hideLoadingIndicator() {
        pbArticleList.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_article_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.action_logout -> {
                viewModel.logout()
                true
            }
            else ->  // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                super.onOptionsItemSelected(item)
        }
    }
}
