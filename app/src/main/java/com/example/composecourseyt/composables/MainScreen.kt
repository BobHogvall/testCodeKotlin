package com.example.composecourseyt.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.composecourseyt.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    val fontFamily = FontFamily(
        Font(R.font.rubik_bold, FontWeight.Bold),
        Font(R.font.rubik_extrabold, FontWeight.ExtraBold),
        Font(R.font.rubik_medium, FontWeight.Medium),
        Font(R.font.rubik_regular, FontWeight.Normal),
        Font(R.font.rubik_semibold, FontWeight.SemiBold),
    )
    val constraints = ConstraintSet {
        val greetingsBox = createRefFor("greetingsbox")
        val mondayBox = createRefFor("mondaybox")
        val tuesdayBox = createRefFor("tuesdaybox")
        val wednesdayBox = createRefFor("wednesdaybox")
        val thursdayBox = createRefFor("thursdaybox")
        val fridayBox = createRefFor("fridaybox")
        createVerticalChain(
            greetingsBox,
            mondayBox,
            tuesdayBox,
            wednesdayBox,
            thursdayBox,
            fridayBox,
            chainStyle = ChainStyle.Spread
        )

        constrain(greetingsBox){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.matchParent
        }
        constrain(mondayBox) {
            top.linkTo(greetingsBox.top)
            start.linkTo(
                greetingsBox.start,
                margin = 48.dp
            ) //better way to center? Might be better with column.
            width = Dimension.percent(0.75f)
            height = Dimension.value(50.dp)
        }
        constrain(tuesdayBox) {
            top.linkTo(mondayBox.bottom)
            start.linkTo(mondayBox.start)
            width = Dimension.percent(0.75f)
            height = Dimension.value(50.dp)
        }
        constrain(wednesdayBox) {
            top.linkTo(tuesdayBox.bottom)
            start.linkTo(tuesdayBox.start)
            width = Dimension.percent(0.75f)
            height = Dimension.value(50.dp)
        }
        constrain(thursdayBox) {
            top.linkTo(wednesdayBox.bottom)
            start.linkTo(wednesdayBox.start)
            width = Dimension.percent(0.75f)
            height = Dimension.value(50.dp)
        }
        constrain(fridayBox) {
            top.linkTo(thursdayBox.bottom)
            start.linkTo(thursdayBox.start)
            width = Dimension.percent(0.75f)
            height = Dimension.value(50.dp)
        }
    }
    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.layoutId("greetingsbox")
        ){
                GreetingSection()
        }
        Box(
            modifier = Modifier
                .background(Color.Green)
                .layoutId("mondaybox"),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Monday",
                fontSize = 24.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(
            modifier = Modifier
                .background(Color.Red)
                .layoutId("tuesdaybox"),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Tuesday",
                fontSize = 24.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .layoutId("wednesdaybox"),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Wednesday",
                fontSize = 24.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(
            modifier = Modifier
                .background(Color.Magenta)
                .layoutId("thursdaybox"),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Thursday",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .layoutId("fridaybox"),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Friday",
                fontSize = 26.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold)
        }
    }
}