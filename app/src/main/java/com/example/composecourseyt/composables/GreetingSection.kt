package com.example.composecourseyt.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GreetingSection(
    name: String = "Bob",
){
    val greeting = getGreetingMessage()
    Row(
       horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$greeting, $name",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.LightGray
            )
            Text(
                text = "We wish you the best of times!",
                style = MaterialTheme.typography.bodySmall,
                color = Color.LightGray
            )
        }
        Icon(
            Icons.Rounded.Search,
            contentDescription = "search function",
            tint = Color.LightGray,
            modifier = Modifier.size(24.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun getGreetingMessage(): String {
    val current = LocalDateTime.now().hour
    return if (current < 12) {
        "Good Morning"
    } else if (current in 12..16) {
        "Good Day"
    } else {
        "Good Evening"
    }
}