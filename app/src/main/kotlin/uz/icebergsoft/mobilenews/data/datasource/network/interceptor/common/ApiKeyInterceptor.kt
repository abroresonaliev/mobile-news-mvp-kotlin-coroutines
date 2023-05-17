package uz.icebergsoft.mobilenews.data.datasource.network.interceptor.common

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response

internal class ApiKeyInterceptor(
    private val apiKey: String = "8eca259240354cd1b70a02b5f7185c62"
) : Interceptor {

    override fun intercept(chain: Chain): Response =
        chain.proceed(addHeaderToRequest(chain.request()))

    private fun addHeaderToRequest(oldRequest: Request): Request = oldRequest.let {
        val original: Request = oldRequest
        val originalHttpUrl = original.url

        val newRequestUrl = originalHttpUrl.newBuilder().apply {
            if (!originalHttpUrl.toUrl().toString().contains("apiKey"))
                addQueryParameter("apiKey", apiKey)
        }.build()

        return@let it.newBuilder().url(newRequestUrl).build()
    }
}