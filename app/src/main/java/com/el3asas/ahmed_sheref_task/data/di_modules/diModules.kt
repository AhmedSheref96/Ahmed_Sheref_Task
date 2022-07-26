package com.el3asas.ahmed_sheref_task.data.di_modules

import android.content.Context
import androidx.room.Room
import com.el3asas.ahmed_sheref_task.data.local.Pref
import com.el3asas.ahmed_sheref_task.data.local.room.DataBase
import com.el3asas.ahmed_sheref_task.data.local.room.MedicineDao
import com.el3asas.ahmed_sheref_task.data.network.EndPoint
import com.el3asas.ahmed_sheref_task.data.network.problems.ProblemsService
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object diSingletonModules {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(EndPoint.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttp(pref: Pref): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(
                LoggingInterceptor.Builder()
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request(" ")
                    .response(" ")
                    .build()
            ).addInterceptor { chain ->
                val url = chain.request().url.newBuilder().build()
                val requestBuilder = chain.request().newBuilder().url(url)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("token", pref.userToken)
                    .build()
                chain.proceed(requestBuilder)
            }
            .connectTimeout(EndPoint.connectTimeout, TimeUnit.MINUTES)
            .writeTimeout(EndPoint.writeTimeout, TimeUnit.MINUTES)
            .readTimeout(EndPoint.readTimeout, TimeUnit.MINUTES)
            .callTimeout(EndPoint.callTimeout, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(context, DataBase::class.java, "medicalAppDB")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

}

@Module
@InstallIn(ViewModelComponent::class)
object diLifeCycleAwaredModules {

    @Provides
    @ViewModelScoped
    fun provideProblemsDao(dataBase: DataBase): MedicineDao = dataBase.medicineDao()

    @Provides
    @ViewModelScoped
    fun provideProblemService(retrofit: Retrofit): ProblemsService =
        retrofit.create(ProblemsService::class.java)

}
