package com.example.rxjavasamples.room.viewmodel;

import android.app.Activity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.example.rxjavasamples.room.db.entity.Contact;

import java.util.List;

public class df {

    public void test(ContactViewModel contactViewModel, LifecycleOwner activity){
        contactViewModel.getAllContacts().observe(activity,
                new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {

            }
        });
    }

}
