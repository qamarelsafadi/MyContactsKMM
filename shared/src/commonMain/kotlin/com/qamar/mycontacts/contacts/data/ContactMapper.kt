package com.qamar.mycontacts.contacts.data

import com.qamar.mycontacts.contacts.domain.Contact
import com.qamar.mycontacts.core.data.ImageStorage
import database.ContactEntity

suspend fun ContactEntity.toContact(imageStorage: ImageStorage): Contact{
    return Contact(
        id = id,
        lastName = lastName,
        firstName =  firstName,
        email =  email,
        mobile =  phone,
        photoBytes = imagePath?.let { imageStorage.getImage(it) }  // TODO: GEt the Image
    )
}