package com.devopworld.todoapp.navigation

import androidx.navigation.NavHostController
import com.devopworld.todoapp.ui.splash.SplashScreen
import com.devopworld.todoapp.util.Action
import com.devopworld.todoapp.util.Constant.LIST_SCREEN
import com.devopworld.todoapp.util.Constant.SPLASH_SCREEN

class Screen(navController: NavHostController) {

    val splash : () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION.name}"){
            popUpTo(SPLASH_SCREEN){
                inclusive = true
            }
        }
    }

    val list: (Int) -> Unit =  { taskId ->
        navController.navigate("task/$taskId")
    }


    val task : (Action) -> Unit ={ action ->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){ inclusive = true}
        }

    }


}