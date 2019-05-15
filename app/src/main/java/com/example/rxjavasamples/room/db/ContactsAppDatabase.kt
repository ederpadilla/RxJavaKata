package com.example.rxjavasamples.room.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.example.rxjavasamples.room.db.daos.ContactDao
import com.example.rxjavasamples.room.db.entity.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactsAppDatabase : RoomDatabase() {

    abstract val contactDAO: ContactDao

}
