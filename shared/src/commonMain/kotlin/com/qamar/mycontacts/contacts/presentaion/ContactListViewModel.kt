package com.qamar.mycontacts.contacts.presentaion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import com.qamar.mycontacts.contacts.domain.Contact
import com.qamar.mycontacts.contacts.domain.ContactDataSource
import com.qamar.mycontacts.contacts.domain.ContactValidator
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val contactDataSource: ContactDataSource
) : ViewModel() {
    private val _state = MutableStateFlow(
        ContactListState(
        )
    )
    val state = combine(
        _state,
        contactDataSource.getContacts(),
        contactDataSource.getRecentContacts(20)
    ) { state, contacts, recentContacts ->
        state.copy(
            contacts = contacts,
            recentlyAddedContacts = recentContacts
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ContactListState())

    var newContact: Contact? by mutableStateOf(null)
        private set

    fun onEvent(event: ContactListEvent) {
        when (event) {
            ContactListEvent.DeleteContact -> {
                viewModelScope.launch {
                    _state.value.selectedContact?.id?.let { id ->
                        _state.update {
                            it.copy(
                                isAddContactSheetOpened = false
                            )
                        }
                        contactDataSource.deleteContact(id)
                        delay(300L)
                        _state.update {
                            it.copy(
                                selectedContact = null
                            )
                        }
                    }
                }
            }

            ContactListEvent.DismissContact -> {
                viewModelScope.launch {
                    _state.update {
                        it.onReset()
                    }
                    delay(300L)
                    newContact = null
                    _state.update {
                        it.copy(
                            selectedContact = null
                        )
                    }
                }
            }

            ContactListEvent.OnAddNewContactClick -> {
                _state.update {
                    it.copy(
                        isAddContactSheetOpened = true
                    )
                }
                newContact = Contact(
                    id = null,
                    firstName = "",
                    lastName = "",
                    email = "",
                    mobile = "",
                    photoBytes = null
                )
            }

            ContactListEvent.OnAddPhotoClicked -> {}
            ContactListEvent.SaveContact -> {
                newContact?.let { contact: Contact ->
                    val result = ContactValidator.validateContact(contact)
                    val error = listOfNotNull(
                        result.firstNameError,
                        result.lastNameError,
                        result.emailError,
                        result.phoneNumberError,

                        )
                    if (error.isEmpty()) {
                        _state.update {
                            it.onReset()
                        }
                        viewModelScope.launch {
                            contactDataSource.insertContact(contact)
                            delay(300L)
                            newContact = null
                        }
                    }else{
                        _state.update {
                            it.copy(
                                firstNameError = result.firstNameError,
                                lastNameError = result.lastNameError,
                                emailNameError = result.emailError,
                                mobileNameError = result.phoneNumberError
                            )
                        }
                    }
                }
            }

            is ContactListEvent.EditContact -> {
                _state.update {
                    it.copy(
                        selectedContact = null,
                        isSelectedContactSheetOpened = false,
                        isAddContactSheetOpened = true
                    )
                }
                newContact = event.contact
            }

            is ContactListEvent.OnEmailChanged -> {
                newContact = newContact?.copy(
                    email = event.value
                )
            }

            is ContactListEvent.OnFirstNameChanged -> {
                newContact = newContact?.copy(
                    firstName = event.value
                )
            }

            is ContactListEvent.OnLastNameChanged -> {
                newContact = newContact?.copy(
                    lastName = event.value
                )
            }

            is ContactListEvent.OnMobileChanged -> {
                newContact = newContact?.copy(
                    mobile = event.value
                )
            }

            is ContactListEvent.OnPhotoPicked -> {
                newContact = newContact?.copy(
                    photoBytes = event.value
                )
            }

            is ContactListEvent.SelectContact -> {
                _state.update {
                    it.copy(
                        selectedContact = event.contact,
                        isSelectedContactSheetOpened = true
                    )
                }
            }
        }
    }
}

