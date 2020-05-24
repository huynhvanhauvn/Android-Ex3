package com.sbro.androidex3.Room

import androidx.room.*
import com.sbro.androidex3.Movie

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(objects: Movie)
    @Delete
    fun deleteMovie(movie: Movie)
}