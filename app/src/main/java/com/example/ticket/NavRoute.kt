package com.example.ticket

sealed class NavRoute(val route: String) {
    object Ticket : NavRoute("ticket")
    object Home : NavRoute("home")
}
