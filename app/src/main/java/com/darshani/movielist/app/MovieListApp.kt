package com.darshani.movielist.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.darshani.movielist.ui.navigation.MovieListNavigation
import com.darshani.movielist.ui.navigation.Route
import com.darshani.movielist.ui.login.LoginScreen
import com.darshani.movielist.ui.moviesList.MovieListScreen
import com.darshani.movielist.ui.signup.SignUpScreen

// This file responsible for all of entry points
@Composable
fun MovieListApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Crossfade(targetState = MovieListNavigation.currentScreen) { currentState ->
            when(currentState.value) {
                is Route.SignUpScreen -> {
                    SignUpScreen(onSignUpClick = { /*TODO*/ }) {

                    }
                }
                is Route.LoginScreen -> {
                    LoginScreen(onLoginClick = { /*TODO*/ }) {
                        
                    }
                }
                is Route.MovieListScreen -> {
                    MovieListScreen()
                }
            }

        }
    }
}