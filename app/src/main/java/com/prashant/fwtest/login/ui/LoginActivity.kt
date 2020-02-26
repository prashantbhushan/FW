package com.prashant.fwtest.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.prashant.fwtest.R
import com.prashant.fwtest.article.ui.list.ArticleListActivity
import com.prashant.fwtest.util.BaseActivity
import com.prashant.fwtest.util.LOGIN_IMAGE_HEADER
import com.prashant.fwtest.util.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpViewModel()
        loadImage()
        btnLogin.setOnClickListener {
            loading.visibility = View.VISIBLE
            viewModel.loginDataChanged(etUserId.text.toString(), etPassword.text.toString())
        }
        viewModel.checkLoginStatus()
    }

    private fun setUpViewModel() {

        viewModel = ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
        viewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer
            loading.visibility = View.GONE
            if (loginState.usernameError != null) {
                etUserId.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                etPassword.error = getString(loginState.passwordError)
            }
        })

        viewModel.isLoginSuccess.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer
            when (loginResult.status) {
                Result.LOADING -> loading.visibility = View.VISIBLE
                Result.SUCCESS -> {
                    loading.visibility = View.GONE
                    launchArticleListActivity()
                    finish()
                }
                Result.ERROR -> {
                    loading.visibility = View.GONE
                    showLoginFailed(it.error)
                }
            }
        })

        viewModel.isLoggedIn.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer
            when (loginResult.status) {
                Result.LOADING -> loading.visibility = View.VISIBLE
                Result.SUCCESS -> {
                    loading.visibility = View.GONE
                    if (it.data == true) {
                        launchArticleListActivity()
                        finish()
                    }
                }
                Result.ERROR -> {
                    loading.visibility = View.GONE
                    showLoginFailed(it.error)
                }
            }
        })
    }

    fun loadImage() {
        Picasso.get()
            .load(LOGIN_IMAGE_HEADER)
            .fit()
            .centerCrop()
            .into(ivLogin)
    }

    private fun showLoginFailed(t: Throwable?) {
        Snackbar.make(
            container,
            t?.message ?: getString(R.string.login_failed),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun launchArticleListActivity() {
        startActivity(Intent(this, ArticleListActivity::class.java))
    }
}
