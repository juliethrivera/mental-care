package com.iegabrielamistral.mentalcare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    val firebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow(firebaseAuth.currentUser)
    val authSet: StateFlow<FirebaseUser?> = _authState

    fun createUser(email: String, password: String) {
        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = firebaseAuth.currentUser
                    } else {
                        _authState.value = null
                    }
                }
        }

    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = firebaseAuth.currentUser
                } else {
                    _authState.value = null
                }


            }
        }
    }
    fun singnOut(){
        firebaseAuth.signOut()
        _authState.value = null
    }

}