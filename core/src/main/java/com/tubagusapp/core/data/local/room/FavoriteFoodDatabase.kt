package com.tubagusapp.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tubagusapp.core.data.local.entity.FavoriteFoodEntity

@Database(entities = [FavoriteFoodEntity::class], version = 1, exportSchema = false)
abstract class FavoriteFoodDatabase : RoomDatabase() {
    abstract fun favoriteFoodDAO(): FavoriteFoodDAO
}