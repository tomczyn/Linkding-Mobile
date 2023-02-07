package com.tomczyn.linkding.android.ui.greeting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tomczyn.linkding.android.ui.common.AppScreen
import com.tomczyn.linkding.android.ui.common.theme.LinkdingTheme

@Composable
fun GreetingScreen() {
    AppScreen {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LinkdingTheme { GreetingScreen() }
}
