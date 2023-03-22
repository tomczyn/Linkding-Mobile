package com.tomczyn.linkding.android.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.tomczyn.linkding.android.ui.common.AppScreen
import com.tomczyn.linkding.android.ui.common.theme.Dimens

@Composable
fun LoginRoute() {
    LoginScreen(
        demoClick = { /*TODO*/ },
        loginClick = { _, _ -> /*TODO*/ },
    )
}

@Composable
fun LoginScreen(
    demoClick: () -> Unit = {},
    loginClick: (String, String) -> Unit = { _, _ -> },
) {
    AppScreen(
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        var host by rememberSaveable { mutableStateOf("") }
        var username by rememberSaveable { mutableStateOf("") }
        LoginTitle()
        Spacer(modifier = Modifier.height(Dimens.marginDouble))
        Column {
            HostField(host) { host = it }
            Spacer(modifier = Modifier.height(Dimens.margin))
            TokenField(username) { username = it }
        }
        Spacer(modifier = Modifier.height(Dimens.marginDouble))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = demoClick) { Text("Demo") }
            Spacer(modifier = Modifier.height(Dimens.marginHalf))
            LoginButton(loginClick = { loginClick(host, username) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HostField(
    host: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = host,
        onValueChange = onValueChange,
        label = { Text("Host") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = KeyboardType.Uri,
            imeAction = ImeAction.Next
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TokenField(
    username: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = username,
        onValueChange = onValueChange,
        label = { Text("Token") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
    )
}

@Composable
private fun LoginButton(
    loginClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .height(Dimens.buttonHeight)
            .fillMaxWidth(0.6f),
        onClick = loginClick,
    ) {
        Text("LOGIN")
    }
}

@Composable
private fun LoginTitle() {
    Text(
        "Login",
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}