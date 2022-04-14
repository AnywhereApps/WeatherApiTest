package com.anywhereapps.project.util

sealed class Status<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T> : Status<T>()
    class Error<T>(message: String, data: T? = null) : Status<T>(data, message)
    class Success<T>(data: T) : Status<T>(data)
    class None<T> : Status<T>()


}