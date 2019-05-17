package com.example.rxjavasamples.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.rxjavasamples.room.db.entity.Contact
import com.example.rxjavasamples.room.repository.ContactRepository


class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val contactRepository = ContactRepository(application)

    fun getAllContacts() : LiveData<List<Contact>>{
        return contactRepository.getContactsListLiveData()
    }

    fun createContact(name : String,email : String){
        contactRepository.createContact(name,email)
    }

    fun updateContact(contact: Contact){
        contactRepository.updateContact(contact)
    }

    fun deleteContact(contact: Contact){
        contactRepository.deleteContact(contact)
    }

    fun disposeObservers(){
        contactRepository.clear()
    }

}