package com.example.rxjavasamples.room.db.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import com.example.rxjavasamples.room.db.entity.Contact
import io.reactivex.Flowable

@Dao
interface ContactDao {

    @get:Query("select * from contacts")
    val contacts: Flowable<List<Contact>>

    @Insert
    fun addContact(contact: Contact): Long

    @Update
    fun updateContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Query("select * from contacts where contact_id== :contactId")
    fun getContact(contactId: Long): Contact
}
