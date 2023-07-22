package com.qamar.mycontacts.contacts.presentaion

import com.qamar.mycontacts.contacts.domain.Contact

data class ContactListState(
    val contacts: List<Contact> = listOf(),
    val recentlyAddedContacts: List<Contact> = listOf(),
    val selectedContact: Contact? = null,
    val isAddContactSheetOpened: Boolean = false,
    val isSelectedContactSheetOpened: Boolean = false,
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailNameError: String? = null,
    val mobileNameError: String? = null,
) {

    fun onReset(): ContactListState {
        return this.copy(
            isSelectedContactSheetOpened = false,
            isAddContactSheetOpened = false,
            firstNameError = null,
            lastNameError = null,
            emailNameError = null,
            mobileNameError = null,
        )
    }
}
