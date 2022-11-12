package uz.icebergsoft.mobilenews.data.datasource.rest.interceptor.logging

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

internal class HttpLoggingInterceptor(isLoggingEnabled: Boolean) : Interceptor {
    private val interceptor = HttpLoggingInterceptor().setLevel(
        if (isLoggingEnabled) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    )

    override fun intercept(chain: Interceptor.Chain): Response =
        interceptor.intercept(chain)
}