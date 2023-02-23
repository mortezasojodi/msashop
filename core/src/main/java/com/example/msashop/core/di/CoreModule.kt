package com.example.msashop.core.di

import androidx.room.Room
import com.example.msashop.core.data.AnimeRepository
import com.example.msashop.core.data.source.local.LocalDataSource
import com.example.msashop.core.data.source.local.room.AnimeDatabase
import com.example.msashop.core.data.source.remote.RemoteDataSource
import com.example.msashop.core.data.source.remote.network.ApiService
import com.example.msashop.core.domain.repository.IAnimeRepository
import com.example.msashop.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//val databaseModule = module {
//    factory { get<AnimeDatabase>().animeDao() }
//    single {
//        val passphrase: ByteArray = SQLiteDatabase.getBytes("masscode".toCharArray())
//        val factory = SupportFactory(passphrase)
//
//        Room.databaseBuilder(
//            androidContext(),
//            AnimeDatabase::class.java, "Anime.db"
//        ).fallbackToDestructiveMigration()
//            .openHelperFactory(factory)
//            .build()
//    }
//}

val networkModule = module {
    single {
//        val hostname = "fakestoreapi.com"
        val certificatePinner = CertificatePinner.Builder()
//            .add(hostname, "sha256/mRjXIIcEJSE3kJl4YNqqfOS+COj4KG3VJPSJo6ymApk=")
//            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
//            .add(hostname, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IAnimeRepository> {
        AnimeRepository(
            get(),
            get(),
            get()
        )
    }
}