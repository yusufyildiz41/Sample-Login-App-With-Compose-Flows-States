package com.yusufyildiz.sampleloginapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yusufyildiz.sampleloginapp.common.Screens
import com.yusufyildiz.sampleloginapp.ui.login.LoginScreen
import com.yusufyildiz.sampleloginapp.ui.signup.SignUpScreen

@Composable
fun LoginNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route,
        modifier = modifier
    ) {

        composable(route = Screens.LoginScreen.route){
            LoginScreen(
                onRegisterTextClicked = {
                    navController.navigateToSignUpScreen()
                }
            )
        }

        composable(route = Screens.SignupScreen.route){
            SignUpScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id){
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateToSignUpScreen() {
    this.navigateSingleTopTo(Screens.SignupScreen.route)
}