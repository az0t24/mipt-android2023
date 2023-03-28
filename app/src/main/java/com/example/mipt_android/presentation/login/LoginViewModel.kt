package com.example.mipt_android.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    /* Прикрутить БД напрямую или через UseCase */
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState = _uiState.asStateFlow()

    fun onTriggerEvent(eventType: LoginEvent) {
        when (eventType) {
            is LoginEvent.LoginInput -> onLoginUpdate(eventType.value)
            is LoginEvent.EmailInput -> onEmailUpdate(eventType.value)
            is LoginEvent.PasswordInput -> onPasswordUpdate(eventType.value)
            is LoginEvent.ChangeAdvertAgreement -> onChangeAdvertAgreement()
            is LoginEvent.ChangeKeepingAgreement -> onChangeKeepingAgreement()
            is LoginEvent.CreateAccount -> onCreateAccount()
            is LoginEvent.AlreadyHaveAccount -> onAlreadyHaveAccount()
        }
    }

    private fun onAlreadyHaveAccount() {
        // Навигация на другой экран
        TODO("Not yet implemented")
    }

    private fun onCreateAccount() {
        // Проверка данных и переход на другой экран
        TODO("Not yet implemented")
    }

    private fun onChangeKeepingAgreement() {
        _uiState.update { current ->
            current.copy(keepingAgreement = !current.keepingAgreement)
        }
    }

    private fun onChangeAdvertAgreement() {
        _uiState.update { current ->
            current.copy(advertAgreement = !current.advertAgreement)
        }
    }

    private fun onLoginUpdate(value: String) {
        _uiState.update { current ->
            current.copy(login = value)
        }
    }

    private fun onEmailUpdate(value: String) {
        _uiState.update { current ->
            current.copy(email = value)
        }
    }

    private fun onPasswordUpdate(value: String) {
        _uiState.update { current ->
            current.copy(password = value)
        }
    }

    private fun onUpdate(value: String) {
        _uiState.update { current ->
            current.copy(login = value)
        }
    }
}