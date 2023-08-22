package com.example.bleremote



sealed class Screen(val route:String){
    object Advertise : Screen(route = "Advertise_screen")
    object Profile : Screen(route = "Profile_screen")
    object Service : Screen(route = "Service_screen")
    object Characteristic : Screen(route = "Characteristic_screen")
}