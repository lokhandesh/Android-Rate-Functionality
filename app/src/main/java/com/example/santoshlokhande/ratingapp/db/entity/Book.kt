package com.example.santoshlokhande.ratingapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
data class Book(
    var title: String,
    var rating:Float
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}