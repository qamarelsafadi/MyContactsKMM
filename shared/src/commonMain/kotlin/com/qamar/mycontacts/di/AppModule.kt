package com.qamar.mycontacts.di

import com.qamar.mycontacts.contacts.domain.ContactDataSource

expect class AppModule {
    val contactDataSource: ContactDataSource
}