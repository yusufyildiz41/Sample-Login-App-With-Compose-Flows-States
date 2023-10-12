package com.yusufyildiz.sampleloginapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufyildiz.sampleloginapp.data.model.UserEntity
import com.yusufyildiz.sampleloginapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface LoginUiState {

    object Loading : LoginUiState

    data class UserLogin(
        val isUserLoggedIn: Boolean, // this input comes from data layer
        val emailValidation: Boolean, // this input comes from UI layer
        val passwordValidation: Boolean, // this input comes from UI layer
        val isRememberMeClicked: Boolean // this input comes from UI layer
    ) : LoginUiState

    object Failed : LoginUiState
}

data class UserInputState(
    val emailValidation: Boolean = false,
    val passwordValidation: Boolean = false,
    val isRememberMeChecked: Boolean = false,
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var userInputState = MutableStateFlow(UserInputState())
    private var _isUserLoggedInState = MutableStateFlow(userRepository.isUserLoggedIn())

    val uiState: StateFlow<LoginUiState> =
        combine(userInputState, _isUserLoggedInState) { userInputValidations, login ->
            when {
                !userInputValidations.emailValidation && !userInputValidations.passwordValidation -> {
                    LoginUiState.Failed
                }

                userInputValidations.emailValidation &&
                        userInputValidations.passwordValidation &&
                        userInputValidations.isRememberMeChecked &&
                        login -> {
                    LoginUiState.UserLogin(
                        isUserLoggedIn = login,
                        emailValidation = userInputValidations.emailValidation,
                        passwordValidation = userInputValidations.passwordValidation,
                        isRememberMeClicked = userInputValidations.isRememberMeChecked
                    )
                }

                else -> {
                    LoginUiState.Loading
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = LoginUiState.UserLogin(
                isUserLoggedIn = false,
                emailValidation = false,
                passwordValidation = false,
                isRememberMeClicked = false
            )
        )

    fun login(email: String, password: String) {
        viewModelScope.launch {
            userRepository.getUsers().collectLatest { userList ->
                val userLogin = userList.map { user ->
                    UserEntity(email = user.email, password = user.password)
                }.contains(
                    UserEntity(email = email, password = password)
                )
                _isUserLoggedInState.value = userRepository.isUserLoggedIn(userLogin)
            }
        }
    }
}