package com.vons.mvvm.frag.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vons.mvvm.R
import com.vons.mvvm.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var dataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentLoginBinding.inflate(inflater)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                dataBinding.login.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    dataBinding.username.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    dataBinding.password.error = getString(it)
                }
            })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                dataBinding.loading.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    dataBinding.username.text.toString(),
                    dataBinding.password.text.toString()
                )
            }
        }
        dataBinding.username.addTextChangedListener(afterTextChangedListener)
        dataBinding.password.addTextChangedListener(afterTextChangedListener)
        dataBinding.password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    dataBinding.username.text.toString(),
                    dataBinding.password.text.toString()
                )
            }
            false
        }

        dataBinding.login.setOnClickListener {
            dataBinding.loading.visibility = View.VISIBLE
            loginViewModel.login(
                dataBinding.username.text.toString(),
                dataBinding.password.text.toString()
            )
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.nickname
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}