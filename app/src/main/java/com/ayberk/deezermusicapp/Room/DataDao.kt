package com.ayberk.deezermusicapp.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ayberk.deezermusicapp.models.album.Data
import com.ayberk.deezermusicapp.models.album.favoriMusic

@Dao
interface DataDao {
    @Query("SELECT * FROM favoriMusic")
    fun getAll(): List<favoriMusic>
    @Delete
    fun delete(advent: favoriMusic)
    @Insert
    fun insert(advent: favoriMusic)

    @Query("SELECT COUNT(*) FROM favoriMusic WHERE id = :id")
    fun checkIfDataExists(id: Int): Int
}