package com.el3asas.ahmed_sheref_task.ui.login

import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.el3asas.utils.base.BaseViewModel
import com.el3asas.utils.utils.customSnackBar
import com.el3asas.utils.utils.navigate
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) :
    BaseViewModel() {
    init {
        isLoading.value = false
    }

    val userName = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun loginUser(
        v: View,
        userNameInputLayout: TextInputLayout,
        passwordInputLayout: TextInputLayout
    ) {
        isLoading.value = true
        when {
            userName.value.isEmpty() -> {
                userNameInputLayout.error = "enter valid user name"
                customSnackBar(
                    v,
                    "please enter valid user name",
                    com.el3asas.utils.R.drawable.ic_outline_error_outline_24
                ) {}
                isLoading.value =false
            }

            password.value.isEmpty() -> {
                passwordInputLayout.error = "enter valid password"
                customSnackBar(
                    v,
                    "please enter valid password",
                    com.el3asas.utils.R.drawable.ic_outline_error_outline_24
                ) {}

                isLoading.value =false
            }

            else -> {
                repository.login(userName = userName.value, password = password.value) {
                    viewModelScope.launch {
                        delay(500)
                        withContext(Dispatchers.Main) {
                            isLoading.value =false
                            openHomeFragment(v)
                        }
                    }
                }
            }
        }

    }

    private fun openHomeFragment(v: View) {
        val dir = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        navigate(v.findNavController(), dir)
    }

    fun onForgetPassClicked(v: View) {

    }

}