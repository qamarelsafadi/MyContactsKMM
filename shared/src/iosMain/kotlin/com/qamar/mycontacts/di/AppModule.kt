package com.qamar.mycontacts.di

import com.qamar.mycontacts.contacts.data.SqlDelightContactDataSource
import com.qamar.mycontacts.contacts.domain.ContactDataSource
import com.qamar.mycontacts.database.ContactDatabase
import com.qamar.mycontacts.core.data.DatabaseDriverFactory
import com.qamar.mycontacts.core.data.ImageStorage

actual class AppModule {

    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactDatabase(
                driver = DatabaseDriverFactory().create()
            ),
            imageStorage = ImageStorage()
        )
    }
}