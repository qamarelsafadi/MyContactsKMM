package com.qamar.mycontacts.contacts.presentaion

import com.qamar.mycontacts.contacts.domain.Contact

sealed interface ContactListEvent {
    object OnAddNewContactClick : ContactListEvent
    object DismissContact : ContactListEvent
    data class OnFirstNameChanged(val value: String) : ContactListEvent
    data class OnLastNameChanged(val value: String) : ContactListEvent
    data class OnEmailChanged(val value: String) : ContactListEvent
    data class OnMobileChanged(val value: String) : ContactListEvent
    data class OnPhotoPicked(val value: ByteArray) : ContactListEvent
    object OnAddPhotoClicked : ContactListEvent
    object SaveContact : ContactListEvent
    data class SelectContact(val contact: Contact) : ContactListEvent
    data class EditContact(val contact: Contact) : ContactListEvent
    object DeleteContact : ContactListEvent

}