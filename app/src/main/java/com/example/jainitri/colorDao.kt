package com.example.jainitri

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ColorDao {
    @Insert
    suspend fun insertColor(color: ColorEntity)

    @Query("SELECT * FROM colors")
    suspend fun getAllColors(): List<ColorEntity>
}
