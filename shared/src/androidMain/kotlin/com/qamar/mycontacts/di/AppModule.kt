package com.qamar.mycontacts.di

import android.content.Context
import com.qamar.mycontacts.contacts.data.SqlDelightContactDataSource
import com.qamar.mycontacts.contacts.domain.ContactDataSource
import com.qamar.mycontacts.core.data.DatabaseDriverFactory
import com.qamar.mycontacts.core.data.ImageStorage
import com.qamar.mycontacts.database.ContactDatabase

actual class AppModule(
    private val context: Context
) {

    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactDatabase(
                driver = DatabaseDriverFactory(context).create(),
            ),
            imageStorage = ImageStorage(context)

        )
    }
}