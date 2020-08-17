package com.santiagoperdomo.cat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "images")
class Image(id: String, url: String) {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    @Expose
    @SerializedName("id")
    var id: String = ""
    @SerializedName("url")
    @Expose
    @ColumnInfo(name = "url")
    var url: String = ""

    init {
        this.id = id
        this.url = url
    }

}