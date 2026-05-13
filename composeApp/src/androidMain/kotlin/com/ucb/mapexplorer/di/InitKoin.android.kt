package com.ucb.mapexplorer.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ucb.mapexplorer.core.data.db.AppDatabase
import com.ucb.mapexplorer.auth.data.dao.AuthDao // Asegúrate de importar esto
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single<AppDatabase> {
        val context = androidContext()
        val dbFile = context.getDatabasePath("dollar_db.db")
        Room.databaseBuilder<AppDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration() // Esto evitará el error de "version mismatch" borrando la BD vieja
            .build()
    }

    single { get<AppDatabase>().getDao() }
    single { get<AppDatabase>().getAuthDao() } // Aquí se provee el AuthDao
}