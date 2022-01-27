package com.drawiin.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drawiin.counter.ui.theme.CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var count by mutableStateOf(0)
            var list  = mutableStateOf(listOf<Int>())
            CounterTheme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Contador: ${count}", style = MaterialTheme.typography.h4)
                    Row {
                        val shouldShow = count > 0
                        val alpha: Float by animateFloatAsState(
                            targetValue = if (shouldShow) 1f else 0.0f,
                            animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
                        )
                        CircularButton(
                            onClick = { if (shouldShow) count-- else 0 },
                            modifier = Modifier.alpha(alpha)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Incrementar"
                            )
                        }
                        Spacer(modifier = Modifier.fillMaxWidth(0.4f))
                        CircularButton(onClick = { list.value = list.value + count++ }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Incrementar"
                            )
                        }
                    }
                    LazyColumn {
                        items(list.value) { item ->
                            CircularButton(onClick = {  }) {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Incrementar"
                                )
                            }
                        }
                        items(list.value) { item ->
                            CircularButton(onClick = {  }) {
                                Text(text = "item: $item")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CircularButton(modifier: Modifier = Modifier , onClick: () -> Unit, icon: @Composable () -> Unit) {
        Button(
            onClick = onClick,
            modifier = modifier
                .clip(CircleShape)
                .size(62.dp)
        ) {
            icon()
        }
}
