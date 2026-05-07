package com.example.foo.testkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform