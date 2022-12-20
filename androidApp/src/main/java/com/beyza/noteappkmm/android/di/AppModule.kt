package com.beyza.noteappkmm.android.di

import android.app.Application
import com.beyza.noteappkmm.data.datasource.SqlDelightDataSource
import com.beyza.noteappkmm.data.local.DatabaseDriverFactory
import com.beyza.noteappkmm.database.NoteDatabase
import com.beyza.noteappkmm.domain.datasource.NoteDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
        return SqlDelightDataSource(NoteDatabase(driver))
    }
}