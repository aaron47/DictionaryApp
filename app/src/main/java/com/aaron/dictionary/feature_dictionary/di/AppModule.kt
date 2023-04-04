package com.aaron.dictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.aaron.dictionary.feature_dictionary.data.local.Converters
import com.aaron.dictionary.feature_dictionary.data.local.WordDefDao
import com.aaron.dictionary.feature_dictionary.data.local.WordDefDatabase
import com.aaron.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.aaron.dictionary.feature_dictionary.data.respository.WordDefRepositoryImpl
import com.aaron.dictionary.feature_dictionary.data.util.GsonParser
import com.aaron.dictionary.feature_dictionary.domain.repository.WordDefRepository
import com.aaron.dictionary.feature_dictionary.domain.use_case.GetWordDefUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordDefRepository(
        api: DictionaryApi,
        db: WordDefDatabase,
    ): WordDefRepository {
        return WordDefRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideGetWordDefUseCase(
        wordDefRepository: WordDefRepository,
    ): GetWordDefUseCase {
        return GetWordDefUseCase(wordDefRepository)
    }

    @Provides
    @Singleton
    fun provideWordDefDatabase(app: Application): WordDefDatabase {
        return Room.databaseBuilder(
            app,
            WordDefDatabase::class.java,
            "word_def_database"
        ).addTypeConverter(Converters(GsonParser(Gson()))).build()
    }
}