package com.example.mipt_android.presentation.login

sealed class LoginEvent() {
    data class LoginInput(val value: String) : LoginEvent()
    data class EmailInput(val value: String) : LoginEvent()
    data class PasswordInput(val value: String) : LoginEvent()
    object ChangeKeepingAgreement : LoginEvent()
    object ChangeAdvertAgreement : LoginEvent()
    object CreateAccount : LoginEvent()
    object AlreadyHaveAccount : LoginEvent()
}