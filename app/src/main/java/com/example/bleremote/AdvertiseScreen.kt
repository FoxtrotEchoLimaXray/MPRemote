package com.example.bleremote

import android.bluetooth.le.ScanSettings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.juul.kable.Advertisement
import com.juul.kable.AndroidAdvertisement
import com.juul.kable.Scanner
import com.juul.kable.peripheral
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun AdvertiseScreen(
    navController: NavController
) {
    Box(modifier=Modifier.fillMaxSize()
    ){
        AdvertiseButton(navController)
    }

}
@Composable
fun AdvertiseButton( navController: NavController,modifier: Modifier=Modifier) {
    /* the scope is tied to the lifecycle of this composable.
     If any coroutine is running when this composable exits composition,
      it will automatically be cancelled.
 */
    val scope = rememberCoroutineScope()
    var job: Job? by remember {
        mutableStateOf(null)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
        ){
        Button(onClick = {
            job = scope.launch {
                try {
                    println("Timer started")
                    val advertisement= startadvertising()
                    if(advertisement!=null){
                        val peripheral = scope.peripheral(advertisement)

                        navController.navigate(route = Screen.Profile.route)
                    }
                } catch (ex: Exception) {
                    println("timer cancelled")
                }
            }
        }) {
            Text("Start Timer")
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            println("Cancelling timer")
            job?.cancel()
        }) {
            Text("Cancel Timer")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AdvertiseScreenPreview() {
    AdvertiseScreen(
        navController = rememberNavController()
    )
}

suspend fun startadvertising(): AndroidAdvertisement? {
    val scanner = Scanner {
        scanSettings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_BALANCED)
            .build()
    }.advertisements.first()
    return scanner
}

