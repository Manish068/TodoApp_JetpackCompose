package com.devopworld.todoapp.ui.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.devopworld.todoapp.R
import com.devopworld.todoapp.ui.theme.taskITemTextColor

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClick: () -> Unit,
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                Button(onClick = {
                    onYesClick()
                    closeDialog()
                })
                {
                    Text(text = stringResource(id = R.string.confirm_yes),color = MaterialTheme.colors.taskITemTextColor)
                }
            },
            dismissButton = {
                            OutlinedButton(onClick = {
                                closeDialog()
                            }) {
                                Text(text = stringResource(id = R.string.confirm_No),color = MaterialTheme.colors.taskITemTextColor)
                            }
            },
            onDismissRequest = { closeDialog() }
        )
    }
}

