package com.sunil.kotlincoroutneexample.injection

import com.google.gson.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sunil.kotlincoroutneexample.BuildConfig
import com.sunil.kotlincoroutneexample.api.ApiWebServices
import com.sunil.kotlincoroutneexample.api.retrofit.RequestInterceptor
import com.sunil.kotlincoroutneexample.api.retrofit.UnsafeOkHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = applicationContext {

    //RequestInterceptor
    bean { provideRequestInterceptor() }

    //LoggingInterceptor
    bean { provideLoggingInterceptor() }

    // provided web components
    bean { provideOkHttpClient(get(), get()) }

    bean { provideGson() }

    bean { provideRemoteDataSource(get(), get()) }
}

fun provideRequestInterceptor(): RequestInterceptor {
    return RequestInterceptor()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return logInterceptor
}

fun provideOkHttpClient(requestInterceptor: RequestInterceptor,
                        logInterceptor: HttpLoggingInterceptor): OkHttpClient {

    val builder = UnsafeOkHttpClient.getUnsafeOkHttpClient()

    // interceptors
    builder.addInterceptor(logInterceptor)
    builder.addInterceptor(requestInterceptor)

    builder.connectTimeout(2, TimeUnit.MINUTES)
    builder.readTimeout(1, TimeUnit.MINUTES)
    builder.readTimeout(1, TimeUnit.MINUTES)

    return builder.build()
}

fun provideGson(): Gson {
    val builder = GsonBuilder()

    builder.registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
        json?.asJsonPrimitive?.asLong?.let {
            return@JsonDeserializer Date(it)
        }
    })

    builder.registerTypeAdapter(Date::class.java, JsonSerializer<Date> { date, _, _ ->
        JsonPrimitive(date.time)
    })

    return builder.create()
}

fun provideRemoteDataSource(okHttpClient: OkHttpClient, gson: Gson): ApiWebServices {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.URL_API)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(ApiWebServices::class.java)
}

