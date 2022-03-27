package uz.icerbersoft.mobilenews.presentation.application.di.data

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import uz.icerbersoft.mobilenews.BuildConfig
import uz.icerbersoft.mobilenews.data.datasource.database.dao.article.ArticleEntityDao
import uz.icerbersoft.mobilenews.data.datasource.database.persistent.AppDatabase
import uz.icerbersoft.mobilenews.data.datasource.rest.interceptor.logging.HttpLoggingInterceptor
import uz.icerbersoft.mobilenews.data.datasource.rest.retrofit.adapter.FlowCallAdapterFactory
import uz.icerbersoft.mobilenews.data.datasource.rest.retrofit.converter.UnitConverterFactory
import uz.icerbersoft.mobilenews.data.datasource.rest.service.ArticleRestService
import uz.icerbersoft.mobilenews.data.utils.json.actual
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal object DataDaggerModuleDataSource {

    @JvmStatic
    @Provides
    @Singleton
    fun appDatabase(
        context: Context
    ): AppDatabase =
        AppDatabase.create(context)

    @JvmStatic
    @Provides
    @Singleton
    fun articleEntityDao(
        appDatabase: AppDatabase
    ): ArticleEntityDao =
        appDatabase.articleEntityDao

    @JvmStatic
    @Provides
    @Singleton
    fun OkHttpClient(
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(BuildConfig.LOG_ENABLED))
            .retryOnConnectionFailure(false)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

    @JvmStatic
    @Provides
    @Singleton
    fun Retrofit(
        okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(FlowCallAdapterFactory)
            .addConverterFactory(Json.Default.actual.asConverterFactory("application/json; charset=utf-8".toMediaType()))
//            .addConverterFactory(converterFactory)
            .addConverterFactory(UnitConverterFactory)
            .build()

    @JvmStatic
    @Provides
    @Singleton
    fun ArticleRestService(
        retrofit: Retrofit
    ): ArticleRestService =
        retrofit.create()

    @JvmStatic
    @Provides
    @Singleton
    fun JsonConverterFactory(
    ): Converter.Factory {
         val lazyJson: Json by lazy {
            Json {
                isLenient = true
                ignoreUnknownKeys = true
                prettyPrint = true
                allowSpecialFloatingPointValues = true
                useArrayPolymorphism = true
            }
        }

        return  lazyJson.asConverterFactory("application/json; charset=utf-8".toMediaType())
    }
}