package com.santiagoperdomo.cat.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santiagoperdomo.cat.model.VoteResponse

@Dao interface VoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(voteResponse: VoteResponse)

    @Query("SELECT * FROM votes")
    fun getVotes(): LiveData<List<VoteResponse>>

    @Query("SELECT * FROM votes WHERE id_image LIKE :idImage")
    fun getVoteByIdImage(idImage: String): LiveData<VoteResponse>

}