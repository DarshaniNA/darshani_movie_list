package com.darshani.movielist.ui.signup

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darshani.movielist.R
import com.darshani.movielist.ui.components.HeaderText
import com.darshani.movielist.ui.components.LoginTextField
import com.darshani.movielist.ui.login.defaultPadding
import com.darshani.movielist.ui.theme.MovieListTheme

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
) {
    val (firstName, onFirstNameChange) = rememberSaveable {
        mutableStateOf("")
    }
    val (lastName, onLastNameChange) = rememberSaveable { mutableStateOf("") }
    val (email, onEmailChange) = rememberSaveable { mutableStateOf("") }
    val (password, onPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, onConfirmPasswordChange) = rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    var isPasswordSame by remember { mutableStateOf(false) }
    val isFieldsNotEmpty = firstName.isNotEmpty() && lastName.isNotEmpty() &&
            email.isNotEmpty() && password.isNotEmpty() &&
            confirmPassword.isNotEmpty()

//    Surface(
//        color = Color.White,
//        modifier = Modifier.fillMaxSize()
//    ) {
//
//    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(isPasswordSame) {
            Text(
                "Password Is not Matching",
                color = MaterialTheme.colorScheme.error,
            )
        }
        HeaderText(
            text =  stringResource(id = R.string.sign_up),
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start)
        )
        LoginTextField(
            value = firstName,
            onValueChange = onFirstNameChange,
            labelText =  stringResource(id = R.string.first_name),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(defaultPadding))
        LoginTextField(
            value = lastName,
            onValueChange = onLastNameChange,
            labelText =  stringResource(id = R.string.last_name),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(defaultPadding))
        LoginTextField(
            value = email,
            onValueChange = onEmailChange,
            labelText =  stringResource(id = R.string.email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(defaultPadding))
        LoginTextField(
            value = password,
            onValueChange = onPasswordChange,
            labelText = stringResource(id = R.string.password),
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password
        )
        Spacer(Modifier.height(defaultPadding))
        LoginTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            labelText =  stringResource(id = R.string.confirm_password),
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password
        )
        Spacer(Modifier.height(defaultPadding + 8.dp))
        Button(
            onClick = {
                isPasswordSame = password != confirmPassword
                if (!isPasswordSame) {
                    onSignUpClick()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFieldsNotEmpty,
        ) {
            Text(stringResource(id = R.string.sign_up))
        }
        Spacer(Modifier.height(defaultPadding))
        val singTx = "Sign In"
        val signInAnnotation = buildAnnotatedString {
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                append(stringResource(id = R.string.already_have_acc),)
            }
            append("  ")
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                pushStringAnnotation(singTx, singTx)
                append(singTx)
            }


        }
        ClickableText(
            signInAnnotation,
        ) { offset ->
            signInAnnotation.getStringAnnotations(offset, offset).forEach {
                if (it.tag == singTx) {
                    Toast.makeText(context, "Sign In", Toast.LENGTH_SHORT).show()
                    onLoginClick()
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevSignUp() {
    MovieListTheme {
        SignUpScreen({}, {})
    }
}