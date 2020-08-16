package com.santiagoperdomo.cat.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.santiagoperdomo.cat.model.Breed
import com.santiagoperdomo.cat.model.Image

@Database(entities = [Breed::class, Image::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun breedDao(): BreedDao
    abstract fun imageDao(): ImageDao
}