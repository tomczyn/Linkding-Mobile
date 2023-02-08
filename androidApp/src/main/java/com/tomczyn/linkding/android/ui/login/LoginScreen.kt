package com.tomczyn.linkding.android.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.tomczyn.linkding.android.ui.common.AppScreen
import com.tomczyn.linkding.android.ui.common.theme.Dimens

@Composable
fun LoginRoute() {
    LoginScreen(
        demoClick = { /*TODO*/ },
        loginClick = { host, username, password -> /*TODO*/ },
    )
}

@Composable
fun LoginScreen(
    demoClick: () -> Unit = {},
    loginClick: (String, String, String) -> Unit = { _, _, _ -> },
) {
    AppScreen(
        verticalArrangement = Arrangement.Center,
    ) {
        var host by rememberSaveable { mutableStateOf("") }
        var username by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        LoginTitle()
        Spacer(modifier = Modifier.height(Dimens.marginDouble))
        HostField(host) { host = it }
        Spacer(modifier = Modifier.height(Dimens.marginHalf))
        UsernameField(username) { username = it }
        Spacer(modifier = Modifier.height(Dimens.marginHalf))
        PasswordField(password) { password = it }
        Spacer(modifier = Modifier.height(Dimens.marginDouble))
        TextButton(onClick = demoClick) { Text("Demo") }
        Spacer(modifier = Modifier.height(Dimens.marginHalf))
        LoginButton(loginClick = { loginClick(host, username, password) })
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
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UsernameField(
    username: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = username,
        onValueChange = onValueChange,
        label = { Text("Username") },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField(
    password: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = password,
        onValueChange = onValueChange,
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
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
    Text("Login", style = MaterialTheme.typography.headlineLarge)
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}