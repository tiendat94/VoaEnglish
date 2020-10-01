package com.example.voaenglish.model

import androidx.room.PrimaryKey
import io.realm.RealmObject

open class Dog(var name: String = "", @PrimaryKey var age: Int = 0) : RealmObject()