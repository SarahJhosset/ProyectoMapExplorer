package com.ucb.mapexplorer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform