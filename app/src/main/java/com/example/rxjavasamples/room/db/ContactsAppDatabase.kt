package com.example.rxjavasamples.room.db

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.rxjavasamples.room.db.daos.ContactDao
import com.example.rxjavasamples.room.db.entity.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactsAppDatabase : RoomDatabase() {

    abstract val contactDAO: ContactDao

}
