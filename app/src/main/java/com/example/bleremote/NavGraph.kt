package com.example.bleremote

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Advertise.route
    ) {

        composable(
            route = Screen.Advertise.route
        ) {
            AdvertiseScreen(navController=navController)
        }

        composable(
            route = Screen.Profile.route
        ) {
            ProfileScreen(navController=navController)
        }
        composable(
            route = Screen.Service.route
        ) {
            ServiceScreen(navController=navController)
        }
        composable(
            route = Screen.Characteristic.route
        ) {
            CharacteristicScreen(navController=navController)
        }
    }
}