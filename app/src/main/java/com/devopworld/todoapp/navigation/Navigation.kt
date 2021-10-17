package com.devopworld.todoapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.devopworld.todoapp.navigation.destination.listComposable
import com.devopworld.todoapp.navigation.destination.splashComposable
import com.devopworld.todoapp.navigation.destination.taskComposable
import com.devopworld.todoapp.ui.viewmodel.SharedViewModel
import com.devopworld.todoapp.util.Constant.LIST_SCREEN
import com.devopworld.todoapp.util.Constant.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navHostController){
        Screen(navHostController)
    }

    AnimatedNavHost(navController = navHostController, startDestination = SPLASH_SCREEN ){

        splashComposable(navigateToListScreen = screen.splash)

        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )

        taskComposable(
            sharedViewModel = sharedViewModel,
            navigateToListScreen = screen.task
        )
    }
}