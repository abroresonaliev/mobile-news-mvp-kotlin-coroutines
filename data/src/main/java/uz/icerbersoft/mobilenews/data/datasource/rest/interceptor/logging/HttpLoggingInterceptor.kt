package uz.icerbersoft.mobilenews.data.datasource.rest.interceptor.logging

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import uz.icerbersoft.mobilenews.data.BuildConfig

internal class HttpLoggingInterceptor : Interceptor {
    private val interceptor = HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    )

    override fun intercept(chain: Interceptor.Chain): Response =
        interceptor.intercept(chain)
}