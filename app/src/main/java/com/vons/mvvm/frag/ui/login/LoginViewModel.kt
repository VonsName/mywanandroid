package com.vons.mvvm.frag.ui.login

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vons.mvvm.R
import com.vons.mvvm.frag.data.LoginDataSource
import com.vons.mvvm.frag.data.LoginRepository
import com.vons.mvvm.frag.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm
    private val loginRepository = LoginRepository(LoginDataSource(context))

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {

        viewModelScope.launch(context = Dispatchers.IO) {
            val result = loginRepository.login(username, password)
            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    _loginResult.value =
                        LoginResult(success = LoggedInUserView(nickname = result.data.nickname))
                } else {
                    _loginResult.value = LoginResult(error = R.string.login_failed)
                }
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}