package com.santiagoperdomo.cat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "breeds")
class Breed(id: String, name: String?, description: String?, origin: String?, temperament: String?, wikipediaUrl: String?): Serializable {

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    @Expose
    var id: String = ""
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    var name: String? = null
    @SerializedName("origin")
    @Expose
    @ColumnInfo(name = "origin")
    var origin: String? = null
    @SerializedName("temperament")
    @Expose
    @ColumnInfo(name = "temperament")
    var temperament: String? = null
    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    var description: String? = null
    @SerializedName("wikipedia_url")
    @Expose
    @ColumnInfo(name = "wikipediaUrl")
    var wikipediaUrl: String? = null
    @Transient
    @Expose(serialize = false)
    @ColumnInfo(name = "url_image")
    var urlImage: String? = null

    init {
        this.id = id
        this.name = name
        this.name = name
        this.description = description
        this.origin = origin
        this.temperament = temperament
        this.wikipediaUrl = wikipediaUrl
    }

}