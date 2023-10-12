package com.yusufyildiz.sampleloginapp.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufyildiz.sampleloginapp.data.model.UserEntity
import com.yusufyildiz.sampleloginapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun addUser(userEntity: UserEntity) {
        viewModelScope.launch {
            userRepository.insertUser(userEntity)
        }
    }
}