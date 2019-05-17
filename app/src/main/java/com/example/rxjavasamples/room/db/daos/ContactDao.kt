package com.example.rxjavasamples.room.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

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
