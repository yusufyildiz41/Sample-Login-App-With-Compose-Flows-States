package com.yusufyildiz.sampleloginapp.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusufyildiz.sampleloginapp.R
import com.yusufyildiz.sampleloginapp.ui.theme.BgColor
import com.yusufyildiz.sampleloginapp.ui.theme.Primary
import com.yusufyildiz.sampleloginapp.ui.theme.SampleLoginAppTheme
import com.yusufyildiz.sampleloginapp.ui.theme.Secondary
import com.yusufyildiz.sampleloginapp.ui.theme.TextColor
import com.yusufyildiz.sampleloginapp.ui.theme.componentShapes

@Composable
fun NormalTextComponent(
    modifier: Modifier = Modifier, value: String
) {
    Text(
        text = value,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp), style = TextStyle(
            fontSize = 18.sp, fontWeight = FontWeight.Normal, fontStyle = FontStyle.Normal
        ), color = TextColor, textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(
    modifier: Modifier = Modifier, value: String
) {
    Text(
        text = value, modifier = modifier.fillMaxWidth(), style = TextStyle(
            fontSize = 30.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Normal
        ), color = TextColor, textAlign = TextAlign.Center
    )
}

@Composable
fun CheckBoxTextComponent(
    modifier: Modifier = Modifier, value: String
) {
    Text(
        text = value, modifier = modifier.fillMaxWidth(), style = TextStyle(
            fontSize = 16.sp, fontWeight = FontWeight.Normal, fontStyle = FontStyle.Normal
        ), textDecoration = TextDecoration.Underline, color = TextColor, textAlign = TextAlign.Start
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    @StringRes labelValue: Int,
    @DrawableRes icon: Int,
    inputText: (String) -> Unit = {}
) {

    val textValue = rememberSaveable { mutableStateOf("") }

    OutlinedTextField(modifier = modifier
        .fillMaxWidth()
        .clip(componentShapes.small),
        value = textValue.value,
        onValueChange = { text: String ->
            textValue.value = text
            inputText(textValue.value)
        },
        label = {
            Text(text = stringResource(id = labelValue))
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = BgColor
        ),
        keyboardOptions = KeyboardOptions.Default,
        leadingIcon = {
            Icon(painter = painterResource(id = icon), contentDescription = "Text Field Icon")
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputTextField(
    modifier: Modifier = Modifier,
    @StringRes labelValue: Int,
    @DrawableRes icon: Int,
    inputText: (String) -> Unit = {}
) {

    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        value = password.value,
        onValueChange = { text: String ->
            password.value = text
            inputText(password.value)
        },
        label = {
            Text(text = stringResource(id = labelValue))
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(painter = painterResource(id = icon), contentDescription = "Text Field Icon")
        },
        trailingIcon = {
            val iconImage =
                if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
    )
}

@Composable
fun CheckBoxComponent(
    modifier: Modifier = Modifier,
    @StringRes value: Int,
    isChecked: (Boolean) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(50.dp)
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        val checkedState = rememberSaveable {
            mutableStateOf(false)
        }
        Checkbox(checked = checkedState.value, onCheckedChange = {
            checkedState.value = !checkedState.value
            isChecked(checkedState.value)
        })

        CheckBoxTextComponent(value = stringResource(id = value))
    }
}

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    @StringRes value: Int,
    onClick: () -> Unit = {}
) {
    ElevatedButton(
        onClick = {
            onClick()
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(45.dp),
        colors = ButtonDefaults.buttonColors(Secondary),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = modifier
                    .padding(top = 11.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                text = stringResource(id = value)
            )
        }
    }

}

@Composable
fun DividerTextComponent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )

        Text(
            modifier = modifier.padding(8.dp),
            text = stringResource(R.string.or),
            fontSize = 18.sp,
            color = TextColor
        )

        Divider(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(
    modifier: Modifier = Modifier,
    onTextSelected: (String) -> Unit
) {
    val initialText = "Already have an account? "
    val loginText = "Login"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }
        })

}

@Composable
fun ClickableRegisterTextComponent(
    modifier: Modifier = Modifier,
    onTextSelected: (String) -> Unit
) {
    val initialText = "Create an account. Click the "
    val registerText = "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = registerText, annotation = registerText)
            append(registerText)
        }
    }

    ClickableText(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 30.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    if (span.item == registerText) {
                        onTextSelected(span.item)
                    }
                }
        })

}

@Composable
fun LoadingProgressBar(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .align(Alignment.Center),
            strokeWidth = 2.dp,
            color = Color.Blue
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NormalTextComponentPreview() {
    SampleLoginAppTheme {
        ButtonComponent(value = R.string.register, onClick = {})
    }
}