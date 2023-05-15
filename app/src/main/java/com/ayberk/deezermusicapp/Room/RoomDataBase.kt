package com.ayberk.deezermusicapp.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayberk.deezermusicapp.Room.DataDao
import com.ayberk.deezermusicapp.models.album.favoriMusic

@Database(entities = [favoriMusic::class], version = 1)
    abstract class RoomDataBase : RoomDatabase() {
        abstract fun dataDao(): DataDao
    }

