package com.darshani.movielist.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Route {
    object LoginScreen : Route()
    object SignUpScreen : Route()
    object MovieListScreen : Route()
}

object MovieListNavigation {

    var currentScreen: MutableState<Route> = mutableStateOf(Route.SignUpScreen)

    fun navigate(destination: Route) {
        currentScreen
    }
}