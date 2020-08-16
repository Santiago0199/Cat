package com.santiagoperdomo.cat.di

import android.app.Application
import androidx.room.Room
import com.santiagoperdomo.cat.api.ApiService
import com.santiagoperdomo.cat.db.BreedDao
import com.santiagoperdomo.cat.db.Database
import com.santiagoperdomo.cat.db.ImageDao
import com.santiagoperdomo.cat.db.VoteDao
import com.santiagoperdomo.cat.util.Constants
import com.santiagoperdomo.cat.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return provideRetrofit(Constants.URL_BASE, provideHttpClient()).create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): Database {
        return Room.databaseBuilder(app, Database::class.java, Constants.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideBreedDao(db: Database): BreedDao {
        return db.breedDao()
    }

    @Singleton
    @Provides
    fun provideImageDao(db: Database): ImageDao {
        return db.imageDao()
    }

    @Singleton
    @Provides
    fun provideVoteDao(db: Database): VoteDao {
        return db.voteDao()
    }
}