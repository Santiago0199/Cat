package com.santiagoperdomo.cat.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santiagoperdomo.cat.model.Breed

@Dao interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breed: Breed)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breeds: List<Breed>)

    @Query("SELECT * FROM breeds b ORDER BY b.name")
    fun getBreeds(): LiveData<List<Breed>>

}