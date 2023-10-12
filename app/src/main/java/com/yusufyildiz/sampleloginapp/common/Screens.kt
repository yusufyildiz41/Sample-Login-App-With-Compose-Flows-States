package com.yusufyildiz.sampleloginapp.common

sealed class Screens(
    val route: String
) {
    object LoginScreen: Screens(route = "login_screen")
    object SignupScreen: Screens(route = "signup_screen")
}