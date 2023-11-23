package com.example.composecourseyt.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composecourseyt.BottomNavItem

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("main_screen") {
            MainScreen()
        }
        composable("chat") {
            ChatScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemCLick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier,
        containerColor = Color.DarkGray,
        tonalElevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemCLick(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Green,
                    unselectedIconColor = Color.LightGray,
                    indicatorColor = Color.DarkGray
                ),
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0) { //badgedBox is experimental => may be changed in the future
                            BadgedBox(badge = { Badge { Text(item.badgeCount.toString()) } }) {
                                Icon(imageVector = item.icon, contentDescription = item.name)
                            }
                        } else {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }
                        if (selected) {
                            Text(text = item.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                        }
                    }
                })
        }
    }
}

@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Chat Screen", color = Color.White)
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings Screen", color = Color.White)
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressBar(percentage = 1f, number = 100, navController = navController)
    }
}

@Composable
fun MainScreen() {
    val constraints = ConstraintSet {
        val mondayBox = createRefFor("mondaybox")
        val tuesdayBox = createRefFor("tuesdaybox")
        val wednesdayBox = createRefFor("wednesdaybox")
        val thursdayBox = createRefFor("thursdaybox")
        val fridayBox = createRefFor("fridaybox")
        createVerticalChain(
            mondayBox,
            tuesdayBox,
            wednesdayBox,
            thursdayBox,
            fridayBox,
            chainStyle = ChainStyle.Spread
        )

        constrain(mondayBox) {
            top.linkTo(parent.top)
            start.linkTo(
                parent.start,
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
            modifier = Modifier
                .background(Color.Green)
                .layoutId("mondaybox"),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Monday",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
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
                fontWeight = FontWeight.Bold
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
                fontWeight = FontWeight.Bold
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
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .layoutId("fridaybox"),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Friday", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        }
    }
}