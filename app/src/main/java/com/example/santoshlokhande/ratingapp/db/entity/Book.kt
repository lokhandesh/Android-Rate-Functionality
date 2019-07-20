package com.example.santoshlokhande.ratingapp.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "books_table")
data class Book(
    var title: String,
    var rating:Float
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}