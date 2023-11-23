package com.example.composecourseyt.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecourseyt.MainActivity

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen"){
            SplashScreen(navController = navController)
        }
        composable("main_screen"){
            MainScreen()
        }
    }
}

@Composable
fun SplashScreen(navController: NavController){
    Box(
       contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        CircularProgressBar(percentage = 0.75f, number = 100, navController = navController )
    }
}

@Composable
fun MainScreen(){
    val constraints = ConstraintSet {
        val mondayBox = createRefFor("mondaybox")
        val tuesdayBox = createRefFor("tuesdaybox")
        val progressBarBox = createRefFor("progressbarbox")
        createVerticalChain(progressBarBox, mondayBox, tuesdayBox)

        constrain(progressBarBox){
            top.linkTo(parent.top)
            start.linkTo(parent.start, margin = 40.dp) //fix how to center, perhaps use guideline
            width = Dimension.value(300.dp)
            height = Dimension.value(300.dp)
        }
        constrain(mondayBox){
            top.linkTo(progressBarBox.bottom)
            start.linkTo(progressBarBox.start)
            width = Dimension.value(300.dp)
            height = Dimension.value(100.dp)
        }

        constrain(tuesdayBox){
            top.linkTo(mondayBox.bottom)
            start.linkTo(mondayBox.start)
            width = Dimension.value(300.dp)
            height = Dimension.value(100.dp)
        }
    }
    ConstraintLayout (constraints, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .layoutId("progressbarbox"),
            contentAlignment = Alignment.Center){
            Text(text = "old progressbar")
        }
        Box(modifier = Modifier
            .background(Color.Green)
            .layoutId("mondaybox"),
            contentAlignment = Alignment.Center){
            Text(
                text = "Monday"
            )
        }
        Box(modifier = Modifier
            .background(Color.Red)
            .layoutId("tuesdaybox"),
            contentAlignment = Alignment.Center){
            Text (
                text = "Tuesday"
            )
        }
    }
}