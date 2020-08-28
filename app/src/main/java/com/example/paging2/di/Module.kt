package com.example.primetime.di

import com.example.paging2.PagingViewModel
import com.example.paging2.Repository
import com.example.paging2.ResultsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {


    viewModel { PagingViewModel(get()) }
    single<OkHttpClient> { provideDefaultOkhttpClient(get()) }
    single<Retrofit> {provideRetrofit(get())  }
    single<ResultsService> {provideService(get())  }
    single<HttpLoggingInterceptor>{ logger() }



}

val repositoryModule = module {
    factory { Repository(get()) }


}
fun provideDefaultOkhttpClient(logger:HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()
}
fun logger():HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BASIC
    return  logger
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://server.mrkzevar.com/gate/b2b/order/api/v1/item/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideService(retrofit: Retrofit): ResultsService = retrofit.create(ResultsService::class.java)

const val DEFAULT_NAMESPACE = "default"