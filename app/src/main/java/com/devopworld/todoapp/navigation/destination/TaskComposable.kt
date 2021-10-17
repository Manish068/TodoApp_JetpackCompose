package com.devopworld.todoapp.navigation.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.devopworld.todoapp.ui.screens.task.TaskScreen
import com.devopworld.todoapp.ui.viewmodel.SharedViewModel
import com.devopworld.todoapp.util.Action
import com.devopworld.todoapp.util.Constant.TASK_ARGUMENT_KEY
import com.devopworld.todoapp.util.Constant.TASK_SCREEN

@ExperimentalAnimationApi
fun NavGraphBuilder.taskComposable(
    sharedViewModel : SharedViewModel,
    navigateToListScreen:(Action) -> Unit
){
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){
            type= NavType.IntType
        }),
        enterTransition = {intiali,final ->
            slideInHorizontally(
                initialOffsetX = { fullwidth->
                    -fullwidth
                },
                animationSpec = tween(
                    300
                )
            )
        }
    ){ navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

        LaunchedEffect(key1 = taskId){
            sharedViewModel.getSelectedTask(taskId = taskId)
        }

        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask ){
            if(selectedTask != null || taskId == -1){
                sharedViewModel.updateSelectedTask(selectedTask = selectedTask)
            }
        }

        TaskScreen(
            selectedTask,
            navigateToListScreen = navigateToListScreen,
            sharedViewModel
        )
    }
}

