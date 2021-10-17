package com.devopworld.todoapp.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import com.devopworld.todoapp.ui.splash.SplashScreen
import com.devopworld.todoapp.util.Constant
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit,
) {
    composable(
        route = Constant.SPLASH_SCREEN,
        exitTransition = { initial, target ->
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight  },
                animationSpec = tween(300)
            )
        }
    ) {
        SplashScreen(navigateToListScreen)
    }


}