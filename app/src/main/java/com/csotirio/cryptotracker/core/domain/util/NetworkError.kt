package com.csotirio.cryptotracker.core.domain.util

sealed class NetworkError : Error {
    object REQUEST_TIMEOUT : NetworkError()
    object TOO_MANY_REQUESTS : NetworkError()
    object NO_INTERNET : NetworkError()
    object SERVER_ERROR : NetworkError()
    object SERIALIZATION : NetworkError()
    object UNKNOWN : NetworkError()
}