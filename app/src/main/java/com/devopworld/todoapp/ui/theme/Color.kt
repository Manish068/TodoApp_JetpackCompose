package com.devopworld.todoapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LowPriorityColor= Color(0xFF43A047);
val HighPriorityColor= Color(0xFFDE44E9);
val MediumPriorityColor= Color(0xFF2196F3);
val NonePriorityColor= Color(0xFFFDFCFC);
val RedColor= Color(0xFFF44336)

val LightBackgroundColor= Color(0xFFF4F6FD)
val LighticonColor = Color(0xFF9D9AB4)
val DarkIconColor= Color(0xFFBBC2D8)
val DarkPrimaryColor= Color(0xFF010319)
val DarkBackgroundColor= Color(0xFF0A155A)

val FabButtonColor= Color(0xFF0568FE)
val PinkColor = Color(0xFFEB06FF)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)



val Colors.splashScreenBackground :Color
    @Composable
    get() = if (isLight) Purple700 else Color.Black



val Colors.topAppBarContentColor :Color
@Composable
get() = if (isLight) DarkPrimaryColor else Color.White

val Colors.topAppBarColor :Color
    @Composable
    get() = if (isLight) Color.White else DarkPrimaryColor

val Colors.fabBackgroundColor :Color
@Composable
get() = if (isLight) FabButtonColor else PinkColor

val Colors.MainBackgroundColor :Color
    @Composable
    get() = if (isLight) LightBackgroundColor else DarkPrimaryColor

val Colors.iconTintColor :Color
@Composable
get() = if (isLight) LighticonColor else DarkIconColor


val Colors.circularBackgroundColor :Color
@Composable
get() = Color.White

val Colors.taskITemTextColor: Color
@Composable
get() = if (isLight) DarkGray else LightGray