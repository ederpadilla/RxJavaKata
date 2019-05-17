package com.example.rxjavasamples.room.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
class Contact {

    @ColumnInfo(name = "contact_name")
    var name: String? = null
    @ColumnInfo(name = "contact_mail")
    var email: String? = null
    @ColumnInfo(name = "contact_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0


    @Ignore
    constructor() {
    }

    constructor(id: Long, name: String, email: String) {
        this.name = name
        this.email = email
        this.id = id
    }
}
