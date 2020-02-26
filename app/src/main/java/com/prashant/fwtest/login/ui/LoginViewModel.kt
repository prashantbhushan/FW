package com.prashant.fwtest.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prashant.fwtest.R
import com.prashant.fwtest.di.scheduler.RunOn
import com.prashant.fwtest.di.scheduler.SchedulerType
import com.prashant.fwtest.login.data.repository.LoginRepository
import com.prashant.fwtest.login.domain.LoginFormState
import com.prashant.fwtest.util.BaseViewModel
import com.prashant.fwtest.util.Result
import io.reactivex.Scheduler
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    val repository: LoginRepository,
    @RunOn(SchedulerType.IO) private val ioScheduler: Scheduler,
    @RunOn(SchedulerType.UI) private val uiScheduler: Scheduler
) : BaseViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private var _isLoginSuccess = MutableLiveData<Result<Boolean>>()
    val isLoginSuccess: LiveData<Result<Boolean>> = _isLoginSuccess

    private var _isLoggedIn = MutableLiveData<Result<Boolean>>()
    val isLoggedIn: LiveData<Result<Boolean>> = _isLoggedIn

    private fun login(userId: String, password: String) {
        disposables.add(
            repository.login(userId, password)
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler)
                .doOnSubscribe({
                    _isLoginSuccess.value = Result.loading()
                })
                .subscribe({
                    _isLoginSuccess.value = Result.success(it)
                }, {
                    _isLoginSuccess.value = Result.error(it)
                })
        )
    }

    fun loginDataChanged(userId: String?, password: String?) {
        if (!isUserIDValid(userId)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_credentials)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_credentials)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
            login(userId ?: "", password ?: "")
        }
    }

    private fun isUserIDValid(userId: String?): Boolean {
        return userId?.isNotBlank() ?: false
    }

    private fun isPasswordValid(password: String?): Boolean {
        return password?.isNotEmpty() ?: false
    }

    fun checkLoginStatus() {
        repository.isUserLoggedIn()
            .observeOn(uiScheduler)
            .subscribeOn(ioScheduler)
            .doOnSubscribe({
                _isLoggedIn.value = Result.loading()
            })
            .subscribe({
                _isLoggedIn.value = Result.success(it)
            }, {
                _isLoggedIn.value = Result.error(it)
            })
    }
}