package com.example.mipt_android.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mipt_android.R
import com.example.mipt_android.app.theme.MiptandroidTheme
import kotlin.reflect.KFunction1

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.pattern),
            contentDescription = "Pattern",
            modifier = Modifier.fillMaxSize()
        )

        FrontDetails(viewModel)
    }
}

@Composable
fun FrontDetails(
    viewModel: LoginViewModel
) {
    val uiState = viewModel.uiState.collectAsState().value

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 33.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_44),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(top = 10.dp, start = 97.dp, end = 104.dp)
                .size(width = 175.dp, height = 139.dp)
        )
        Text(
            text = stringResource(id = R.string.food_ninja),
            color = colorResource(id = R.color.main_color),
            style = MaterialTheme.typography.h1,
            fontSize = 40.sp
        )
        Text(
            text = stringResource(id = R.string.slogan),
            color = colorResource(id = R.color.black_2),
            style = MaterialTheme.typography.h2,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.heightIn(65.dp))
        Text(
            text = stringResource(id = R.string.sign_up_title),
            color = colorResource(id = R.color.black_2),
            style = MaterialTheme.typography.h2,
            fontSize = 20.sp
        )
        Column(
            modifier = Modifier
                .padding(top = 40.dp, start = 25.dp, end = 25.dp)
                .fillMaxWidth()
        ) {
            InputLoginField(
                event = { viewModel.onTriggerEvent(LoginEvent.LoginInput(it)) },
                uiState = uiState.login,
                title = stringResource(id = R.string.login_field),
                leadingIcon = Icons.Rounded.Person
            )
            Spacer(modifier = Modifier.heightIn(12.dp))
            InputLoginField(
                event = { viewModel.onTriggerEvent(LoginEvent.EmailInput(it)) },
                uiState = uiState.email,
                title = stringResource(id = R.string.email_field),
                leadingIcon = Icons.Rounded.Email
            )
            Spacer(modifier = Modifier.heightIn(12.dp))
            InputLoginField(
                event = { viewModel.onTriggerEvent(LoginEvent.PasswordInput(it)) },
                uiState = uiState.password,
                title = stringResource(id = R.string.password_field),
                leadingIcon = Icons.Rounded.Lock,
                trailingIcon = Icons.Rounded.Info
            )
            Spacer(modifier = Modifier.heightIn(20.dp))
            CheckOption(
                event = { viewModel.onTriggerEvent(LoginEvent.ChangeKeepingAgreement) },
                uiState = uiState.keepingAgreement,
                text = stringResource(id = R.string.sign_in_agreement1)
            )
            Spacer(modifier = Modifier.heightIn(12.dp))
            CheckOption(
                event = { viewModel.onTriggerEvent(LoginEvent.ChangeAdvertAgreement) },
                uiState = uiState.advertAgreement,
                text = stringResource(id = R.string.sign_in_agreement2)
            )
            Spacer(modifier = Modifier.heightIn(12.dp))
            SignIn(
                onCreateAccount = { viewModel.onTriggerEvent(LoginEvent.CreateAccount) },
                onAlreadyHaveAccount = { viewModel.onTriggerEvent(LoginEvent.AlreadyHaveAccount) }
            )
        }
    }
}

@Composable
fun CheckOption(
    modifier: Modifier = Modifier,
    event: () -> Unit,
    uiState: Boolean,
    text: String
) {
    Row() {
        IconButton(
            onClick = event,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(22.dp)
        ) {
            if (uiState)
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Accepted",
                    tint = colorResource(id = R.color.main_color)
                )

            else
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Not accepted",
                    tint = colorResource(id = R.color.gray_2)
                )
        }
        Text(
            text = text
        )
    }
}

@Composable
fun InputLoginField(
    modifier: Modifier = Modifier,
    event: (String) -> Unit,
    title: String,
    uiState: String,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector? = null
) {
    OutlinedTextField(
        value = uiState,
        onValueChange = event,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = title,
                modifier = Modifier.padding(start = 20.dp, end = 5.dp)
            )
        },
        trailingIcon = if (trailingIcon != null) {
            {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = "see",
                    modifier = Modifier.padding(end = 5.dp)
                )
            }
        } else { null },
        label = {
            Text(
                text = title,
                color = colorResource(id = R.color.gray_2),
                style = MaterialTheme.typography.h2,
                fontSize = 14.sp
            )
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.light_gray),
            unfocusedBorderColor = colorResource(id = R.color.light_gray),
            placeholderColor = colorResource(id = R.color.light_gray)
        )
    )
}

@Composable
fun SignIn(
    modifier: Modifier = Modifier,
    onCreateAccount: () -> Unit,
    onAlreadyHaveAccount: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = modifier.height(12.dp))
        Button(
            onClick = onCreateAccount,
            modifier = modifier
                .size(175.dp, 57.dp)
                .clip(shape = RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.main_color)
            )
        ) {
            Text(
                text = stringResource(id = R.string.create_account),
                color = colorResource(id = R.color.white),
                style = MaterialTheme.typography.h2
            )
        }
        Spacer(modifier = modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.have_account),
            textDecoration = TextDecoration.Underline,
            modifier = modifier.clickable(onClick = onAlreadyHaveAccount),
            color = colorResource(id = R.color.main_color)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    MiptandroidTheme {
        LoginScreen()
    }
}