package com.ucb.mapexplorer.login.domain.usecase

import com.ucb.mapexplorer.login.domain.repository.LoginRepository

class LoginUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(user: String, pass: String): Boolean {
        return repository.login(user, pass)
    }
}