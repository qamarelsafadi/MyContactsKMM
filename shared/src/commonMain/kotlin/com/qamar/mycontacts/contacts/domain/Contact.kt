package com.qamar.mycontacts.contacts.domain

data class Contact(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val mobile: String,
    val photoBytes: ByteArray?
)