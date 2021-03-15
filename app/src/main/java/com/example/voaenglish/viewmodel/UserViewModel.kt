package com.example.voaenglish.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.voaenglish.base.BaseViewModelKotlin
import com.example.voaenglish.model.UserKotlin
import kotlinx.coroutines.launch

class UserViewModel() : BaseViewModelKotlin() {

    private val repository: WordRepository? = null

    init {

    }

    fun insert(userKotlin: UserKotlin) = viewModelScope.launch {
        //repository.insert(userKotlin)
    }
}

class UserViewModelFatory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}