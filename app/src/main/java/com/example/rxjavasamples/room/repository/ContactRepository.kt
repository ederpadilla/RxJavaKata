package com.example.rxjavasamples.room.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.rxjavasamples.room.db.ContactsAppDatabase
import com.example.rxjavasamples.room.db.entity.Contact
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

class ContactRepository(private val application: Application) {

    private val contactsAppDatabase: ContactsAppDatabase

    private var compositeDisposable : CompositeDisposable = CompositeDisposable()

    private val contactsLiveData = MutableLiveData<List<Contact>>()

    private var rowIdOfInsertedMethod : Long = 0

    init {
        contactsAppDatabase = Room.databaseBuilder(
            this.application.applicationContext,
            ContactsAppDatabase::class.java, "ContactDB"
        ).build()
        compositeDisposable.add(
            contactsAppDatabase.contactDAO.contacts.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ contactList ->
                    contactsLiveData.postValue(contactList)
                }, { throwable -> throwable.printStackTrace() })
        )
    }

    fun getContactsListLiveData() : MutableLiveData<List<Contact>>{
        return contactsLiveData
    }

    public fun createContact(name: String, email: String) {
        compositeDisposable.add(
            Completable.fromAction { rowIdOfInsertedMethod =
                contactsAppDatabase.contactDAO
                    .addContact(Contact(0, name, email))
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableCompletableObserver(){
                    override fun onComplete() {
                        Toast.makeText(application.applicationContext,
                            "Contact added successfully $rowIdOfInsertedMethod",
                            Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Toast.makeText(application.applicationContext,
                            "${e.message}",
                            Toast.LENGTH_SHORT).show()
                    }

                })
        )
    }

    fun updateContact(contact: Contact) {
        compositeDisposable.add(
            Completable.fromAction {
                contactsAppDatabase.contactDAO.updateContact(contact)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableCompletableObserver(){
                    override fun onComplete() {
                        Toast.makeText(application.applicationContext,
                            "Contact updated successfully $rowIdOfInsertedMethod",
                            Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Toast.makeText(application.applicationContext,
                            "${e.message}",
                            Toast.LENGTH_SHORT).show()
                    }

                })
        )
    }

    fun deleteContact(contact: Contact) {
        compositeDisposable.add(
            Completable.fromAction {
                contact.let {
                    contactsAppDatabase.contactDAO.deleteContact(it)
                }
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableCompletableObserver(){
                    override fun onComplete() {
                        Toast.makeText(application.applicationContext,
                            "Contact deleted successfully $rowIdOfInsertedMethod",
                            Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Toast.makeText(application.applicationContext,
                            "${e.message}",
                            Toast.LENGTH_SHORT).show()
                    }

                })
        )
    }

    fun clear(){
        compositeDisposable.clear()
    }

}
