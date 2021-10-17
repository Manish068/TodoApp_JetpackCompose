package com.devopworld.todoapp.data.model

import androidx.compose.ui.graphics.Color
import com.devopworld.todoapp.ui.theme.HighPriorityColor
import com.devopworld.todoapp.ui.theme.LowPriorityColor
import com.devopworld.todoapp.ui.theme.MediumPriorityColor
import com.devopworld.todoapp.ui.theme.NonePriorityColor

enum class Priority(val Color: Color){
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}