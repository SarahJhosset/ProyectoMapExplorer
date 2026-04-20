package com.ucb.mapexplorer.login.domain.repository

interface LoginRepository {
    suspend fun login(user: String, pass: String): Boolean
}