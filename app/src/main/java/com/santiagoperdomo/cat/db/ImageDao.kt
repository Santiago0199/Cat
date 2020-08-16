package com.santiagoperdomo.cat.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santiagoperdomo.cat.model.Image

@Dao interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: Image)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<Image>)

    @Query("SELECT * FROM images")
    fun getImages(): LiveData<List<Image>>

}