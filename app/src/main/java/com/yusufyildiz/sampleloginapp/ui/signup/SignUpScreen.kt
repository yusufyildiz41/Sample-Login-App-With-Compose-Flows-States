package com.yusufyildiz.sampleloginapp.ui.signup

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yusufyildiz.sampleloginapp.R
import com.yusufyildiz.sampleloginapp.data.model.UserEntity
import com.yusufyildiz.sampleloginapp.ui.components.ButtonComponent
import com.yusufyildiz.sampleloginapp.ui.components.CheckBoxComponent
import com.yusufyildiz.sampleloginapp.ui.components.ClickableLoginTextComponent
import com.yusufyildiz.sampleloginapp.ui.components.DividerTextComponent
import com.yusufyildiz.sampleloginapp.ui.components.HeadingTextComponent
import com.yusufyildiz.sampleloginapp.ui.components.InputTextField
import com.yusufyildiz.sampleloginapp.ui.components.NormalTextComponent
import com.yusufyildiz.sampleloginapp.ui.components.PasswordInputTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val snackBarHostState = remember { SnackbarHostState() }
    var firstNameInput by remember { mutableStateOf("") }
    var lastNameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        content = { contentPadding ->

            Surface(
                color = Color.White,
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(28.dp)
                    .padding(contentPadding)
            ) {

                Column(modifier = modifier.fillMaxSize()) {
                    NormalTextComponent(value = stringResource(id = R.string.hello))
                    HeadingTextComponent(value = stringResource(id = R.string.create_account))
                    Spacer(modifier = modifier.height(20.dp))
                    InputTextField(
                        labelValue = R.string.first_name,
                        icon = R.drawable.profile_icon,
                        inputText = { firstName ->
                            firstNameInput = firstName
                        }
                    )
                    InputTextField(
                        labelValue = R.string.last_name,
                        icon = R.drawable.profile_icon,
                        inputText = { lastName ->
                            Log.e("success",lastName)
                            lastNameInput = lastName
                        }
                    )
                    InputTextField(
                        labelValue = R.string.email,
                        icon = R.drawable.email_icon,
                        inputText = { email ->
                            emailInput = email
                        }
                    )
                    PasswordInputTextField(
                        labelValue = R.string.password,
                        icon = R.drawable.password_icon,
                        inputText = { password ->
                            passwordInput = password
                        }
                    )
                    PasswordInputTextField(
                        labelValue = R.string.password_again,
                        icon = R.drawable.password_icon,
                        inputText = { passwordAgain ->

                        }
                    )
                    CheckBoxComponent(value = R.string.terms_and_conditions)
                    Spacer(modifier = Modifier.height(80.dp))
                    ButtonComponent(
                        value = R.string.register,
                        onClick = {
                            viewModel.addUser(
                                UserEntity(
                                    firstName = firstNameInput,
                                    lastName = lastNameInput,
                                    password = passwordInput,
                                    email = emailInput
                                )
                            )
                            /*
                                                        scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = "Clicked",
                                    duration = SnackbarDuration.Short
                                )
                            }
                             */
                        })
                    Spacer(modifier = Modifier.height(25.dp))
                    DividerTextComponent()
                    ClickableLoginTextComponent(onTextSelected = {

                    })
                }
            }
        }
    )

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}