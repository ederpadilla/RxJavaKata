package com.example.rxjavasamples.room.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

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
