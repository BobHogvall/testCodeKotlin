package com.example.composecourseyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.composecourseyt.composables.CircularProgressBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                    CircularProgressBar(percentage = 0.75f, number = 100) // look up how to set this as first page with EffectHandlers
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
    }
}