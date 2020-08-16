package com.santiagoperdomo.cat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "votes")
class VoteResponse {

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    @Expose
    var id: Long = 0
    @SerializedName("message")
    @Expose
    var message: String? = null
    @Transient
    @Expose(serialize = false)
    @ColumnInfo(name = "date")
    var date: String? = null
    @Transient
    @Expose(serialize = false)
    @ColumnInfo(name = "id_image")
    var idImage: String = ""

}