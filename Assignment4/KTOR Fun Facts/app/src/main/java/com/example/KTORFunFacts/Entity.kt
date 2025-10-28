package com.example.KTORFunFacts

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
@Entity(tableName = "funFacts")
data class FunFact (@PrimaryKey(autoGenerate = true) @Transient val id: Int = 0,
                    var text:String,
                    var source_url:String?=null)

