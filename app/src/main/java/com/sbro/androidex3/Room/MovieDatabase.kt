package com.sbro.androidex3.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sbro.androidex3.Movie

@Database(entities = [Movie::class],version = 3)
abstract class MovieDatabase:RoomDatabase() {

    abstract  fun movieDAO(): MovieDAO

    companion object{
        @Volatile

        private var instance : MovieDatabase?=null;
        private val LOCK = Any()

        operator fun invoke(context: Context): MovieDatabase = instance
            ?: synchronized(LOCK){
            instance
                ?: buildDatabase(
                    context
                ).also { instance =it}
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            MovieDatabase::class.java,"movie_information"
        ).allowMainThreadQueries()
            .build()
    }
}