package com.devopworld.todoapp.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.devopworld.todoapp.data.model.Priority
import com.devopworld.todoapp.ui.theme.Typography
import com.devopworld.todoapp.ui.theme.circularBackgroundColor

@Composable
fun PriorityItem(
    priority: Priority
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            backgroundColor = MaterialTheme.colors.circularBackgroundColor,
            modifier = Modifier
                .size(20.dp)
                .testTag(tag = "circle").padding(4.dp),


            // below line is use to
            // add shape to our image view.
            shape = CircleShape,

            // below line is use to add
            // elevation to our image view.
            elevation = 4.dp
        ) {
            Canvas(modifier = Modifier
                .size(18.dp)
                .padding(2.dp)) {
                drawCircle(color = priority.Color)
            }
        }

        Text(text = priority.name,
            style = Typography.subtitle1,
            modifier = Modifier.padding(start = 7.dp),
        )
    }
}

@Composable
@Preview
private fun PriorityItemPreview(){
    PriorityItem(priority = Priority.HIGH)
}