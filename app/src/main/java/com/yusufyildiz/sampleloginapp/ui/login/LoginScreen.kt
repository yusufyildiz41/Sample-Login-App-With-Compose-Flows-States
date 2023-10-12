package com.yusufyildiz.sampleloginapp.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yusufyildiz.sampleloginapp.R
import com.yusufyildiz.sampleloginapp.ui.components.ButtonComponent
import com.yusufyildiz.sampleloginapp.ui.components.CheckBoxComponent
import com.yusufyildiz.sampleloginapp.ui.components.ClickableRegisterTextComponent
import com.yusufyildiz.sampleloginapp.ui.components.DividerTextComponent
import com.yusufyildiz.sampleloginapp.ui.components.HeadingTextComponent
import com.yusufyildiz.sampleloginapp.ui.components.InputTextField
import com.yusufyildiz.sampleloginapp.ui.components.LoadingProgressBar
import com.yusufyildiz.sampleloginapp.ui.components.NormalTextComponent
import com.yusufyildiz.sampleloginapp.ui.components.PasswordInputTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onRegisterTextClicked: () -> Unit = {}
) {
    val snackbarHostState = remember {SnackbarHostState()}
    val loginUiState by viewModel.uiState.collectAsStateWithLifecycle()
    var userEmail by rememberSaveable { mutableStateOf("") }
    var userPassword by rememberSaveable { mutableStateOf("") }
    var isRememberMeChecked by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = { contentPadding ->

            Surface(
                color = Color.White,
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(28.dp)
                    .padding(contentPadding)
            ){
                when(loginUiState) {
                    is LoginUiState.Loading -> {
                        LoadingProgressBar()
                    }

                    is LoginUiState.Failed -> {
                        println("failed")
                    }

                    is LoginUiState.UserLogin -> {
                        println((loginUiState as LoginUiState.UserLogin).isUserLoggedIn)
                    }
                }

                Column(
                    modifier = modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    NormalTextComponent(value = stringResource(id = R.string.hello))
                    HeadingTextComponent(value = stringResource(id = R.string.login_message))
                    Spacer(modifier = modifier.height(20.dp))
                    InputTextField(
                        labelValue = R.string.email,
                        icon = R.drawable.email_icon,
                        inputText = { email ->
                            userEmail = email
                        }
                    )
                    PasswordInputTextField(
                        labelValue = R.string.password,
                        icon = R.drawable.password_icon,
                        inputText = { password ->
                            userPassword = password
                        }
                    )
                    CheckBoxComponent(
                        value = R.string.remember_me,
                        isChecked = {
                            isRememberMeChecked = it
                        }
                    )
                    Spacer(modifier = modifier.height(80.dp))
                    ButtonComponent(
                        value = R.string.login_button_message,
                        onClick = {
                            if((userEmail.isNotEmpty() && userPassword.isNotEmpty())){
                                viewModel.userInputState.value = UserInputState(emailValidation = true,passwordValidation = true,isRememberMeChecked = isRememberMeChecked)
                                viewModel.login(userEmail,userPassword)
                            } else {
                                viewModel.userInputState.value = UserInputState(emailValidation = false,passwordValidation = false)
                            }
                        }
                    )
                    Spacer(modifier = modifier.height(35.dp))
                    DividerTextComponent()
                    ClickableRegisterTextComponent(
                        onTextSelected = {
                            onRegisterTextClicked()
                        }
                    )
                }
            }

        }
    )

}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    LoadingProgressBar()
}