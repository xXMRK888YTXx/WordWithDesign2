package com.xxmrk888ytxx.workwithdesing2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.random.nextInt


data class ScreenState(
    val daysAgo:Int = 6,
    val recommenderUser:Int = 19,
    val recommenderBy:Int = 40,
    val views:Int = 1000,
    val comments:Int = 1000,
    val likes:Int = 1000
)

val gradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFFFF3D00),
        Color(0xFFE35C32),

        )
)

class MainActivity : ComponentActivity() {


    private val currentScreenState = mutableStateOf(ScreenState())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val state by remember {
                currentScreenState
            }

            Profile(
                state
            )
        }
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            while (isActive) {
                val newState = ScreenState(
                    daysAgo = Random(System.currentTimeMillis()).nextInt(1..99),
                    recommenderUser = Random(System.currentTimeMillis()).nextInt(1..56),
                    recommenderBy = Random(System.currentTimeMillis()).nextInt(1..99),
                    views = Random(System.currentTimeMillis()).nextInt(1..3000),
                    comments = Random(System.currentTimeMillis()).nextInt(1..3000),
                    likes = Random(System.currentTimeMillis()).nextInt(1..3000)
                )

                withContext(Dispatchers.Main) {
                    currentScreenState.value = newState
                }

                delay(5000)
            }
        }
    }
}

@Composable
fun Profile(
    screenState:ScreenState
) {

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .weight(0.6f)
                .background(Color.Yellow)
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding()
                .weight(0.35f)
                .background(Color.Black)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
            ) {
                Text(
                    text = "Model winner of 2018.\n" +
                            "Tokyo Art costume design week",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.W900,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(Color.Red)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Izablella Znang",
                            color = Color.White,
                            fontSize = 20.sp
                        )

                        Text(
                            text = "${screenState.daysAgo} days ago",
                            color = Color.White.copy(0.5f)
                        )

                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(35.dp)
                            .clip(CircleShape)
                            .background(gradient)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                2.dp,
                                Alignment.CenterHorizontally
                            ),
                            modifier = Modifier.fillMaxSize()
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.baseline_add_24),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )

                            Text(
                                text = "Follow",
                                color = Color.White,
                                fontWeight = FontWeight.W900
                            )
                        }
                    }
                }
            }


            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                color = Color.White.copy(0.1f)
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        end = 10.dp,
                        start = 10.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Recommended by:", color = Color.White.copy(0.5f))

                Spacer(modifier = Modifier.weight(1f))

                Text(text = "${screenState.recommenderUser} / 56 users", color = Color.White.copy(0.5f))


            }

            Spacer(modifier = Modifier.weight(1f))



            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                CircleWidget(
                    modifier = Modifier
                    .padding(10.dp),
                    screenState.recommenderBy
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.baseline_visibility_24),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )

                Text(text = "${screenState.views}", color = Color.White)

                Icon(
                    painter = painterResource(id = R.drawable.baseline_brightness_1_24),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )

                Text(text = "${screenState.comments}", color = Color.White)

                Icon(
                    painter = painterResource(id = R.drawable.baseline_heart_broken_24),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )

                Text(text = "${screenState.likes}", color = Color.White)

            }

        }
    }

    Box(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun CircleWidget(
    modifier: Modifier,
    count: Int,
    circleSize: Dp = 30.dp
) {
    val iterations = if (count <= 4) count else 4

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(iterations) {
            Canvas(modifier = Modifier
                .size(circleSize)
                .offset(if (it != 0) (-10 * it).dp + it.dp else 0.dp), onDraw = {
                drawCircle(randomColor())
            })
        }

        if (count > 4) {

            Box(
                modifier = Modifier
                    .size(circleSize + 5.dp)
                    .offset((-10 * 5).dp + 15.dp),
                contentAlignment = Alignment.Center
            ) {
                Box() {
                    Canvas(modifier = Modifier
                        .size(circleSize + 1.dp), onDraw = {
                        drawCircle(Color.DarkGray)
                    })
                }


                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "+${minOf(count - 4,99)}", color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}

fun randomColor(): Color {
    return Color(
        red = Random(System.currentTimeMillis()).nextInt(0..255),
        green = Random(System.currentTimeMillis()).nextInt(0..255),
        blue = Random(System.currentTimeMillis()).nextInt(0..255)
    )
}
