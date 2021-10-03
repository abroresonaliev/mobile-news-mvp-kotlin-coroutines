package uz.icerbersoft.mobilenews.data.datasource.rest

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import uz.icerbersoft.mobilenews.data.datasource.rest.interceptor.logging.HttpLoggingInterceptor
import uz.icerbersoft.mobilenews.data.datasource.rest.retrofit.adapter.FlowCallAdapterFactory
import uz.icerbersoft.mobilenews.data.datasource.rest.retrofit.converter.UnitConverterFactory
import uz.icerbersoft.mobilenews.data.datasource.rest.service.RestService
import uz.icerbersoft.mobilenews.data.utils.json.actual
import java.util.concurrent.TimeUnit

internal class RestProviderImpl : RestProvider {

    private val okHttpClientBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .retryOnConnectionFailure(false)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)

    private val retrofit: Retrofit by lazy {
        @Suppress("EXPERIMENTAL_API_USAGE")
        return@lazy Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClientBuilder.build())
            .addCallAdapterFactory(FlowCallAdapterFactory)
            .addConverterFactory(Json.actual.asConverterFactory("application/json; charset=utf-8".toMediaType()))
            .addConverterFactory(UnitConverterFactory)
            .build()
    }

    override val restService: RestService =
        retrofit.create(RestService::class.java)

    private companion object {
        const val CONNECTION_TIMEOUT_SECONDS: Long = 60
        const val API_BASE_URL: String = "https://newsapi.org/v2/"
    }
}