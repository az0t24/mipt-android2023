package com.example.mipt_android.presentation.login

data class LoginState(
    val login: String = "",
    val email: String = "",
    val password: String = "",
    val keepingAgreement: Boolean = false,
    val advertAgreement: Boolean = false,
)