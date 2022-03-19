package com.example.ticket.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ticket.R
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("InvalidColorHexValue")
@Composable
fun Ticket(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Row {
            Back()
            QrCode()
        }

        }
    }



@Composable
private fun Circle() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .wrapContentSize(Alignment.CenterEnd)
                .align(Alignment.TopCenter)
                .background(Color(0xFFFFFFFF))
        ) {
            Image(
                painterResource(R.drawable.logo2),
                "content description",
                modifier = Modifier.align(Alignment.BottomCenter),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
fun Squares(modifier: Modifier) {
    val rectSize = 40.dp
    Canvas(modifier = modifier.clipToBounds()) {
        translate(size.width / 2, size.height / 2) {
            drawRect(
                brush = SolidColor(Color.Black),
                Offset(-(rectSize / 2).toPx(), -(rectSize / 2).toPx()),
                Size(rectSize.toPx(), rectSize.toPx()),
                style = Stroke(width = 2f)
            )
        }
    }
}

@Composable
fun Back() {
    Box(modifier = Modifier
        .clip(CircleShape)
        .background(Color(0xFFFFFFFF))
    ) {
        Image(
            ImageVector.vectorResource(id = R.drawable.back),
            "",
            modifier = Modifier.align(Alignment.BottomCenter),
            colorFilter = ColorFilter.tint(
                Color(0xFF000000),

                )
        )
    }
}

@Composable
fun QrCode() {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .padding(16.dp)
            .background(Color(0xFFFFFFFF))
    ) {
        Image(
            ImageVector.vectorResource(id = R.drawable.qr),
            "",
            modifier = Modifier.align(Alignment.Center),
            colorFilter = ColorFilter.tint(
                Color(0xFF000000),
            )
        )
    }
}
@Composable
fun SecondCard() {

    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val simpleTimeFormat = SimpleDateFormat("hh:MM")

    val currentDT: String = simpleDateFormat.format(Date())
    val currentTime: String = simpleTimeFormat.format(Date())

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(25))
            .fillMaxWidth()
            .height(100.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFFF))
        ) {
            Text(
                text = "For RTC only",
                modifier = Modifier.fillMaxHeight(0.20f),
                fontSize = 10.sp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.60f),

            ) {
                Image(
                    ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
                    "",
                    modifier = Modifier.fillMaxWidth(0.33f)
                )
                Column(
                    modifier = Modifier.fillMaxWidth(0.33f)
                ) {
                    Text(
                        currentDT,
                        modifier = Modifier.width(100.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        currentTime.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.33f))
            }
            Text(
                text = "1-234FA2987BA4",
                modifier = Modifier.fillMaxHeight(0.99f)
            )
        }
    }
}
@Preview
@Composable
fun FirstCard() {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val simpleTimeFormat = SimpleDateFormat("hh:MM")

    val currentDT: String = simpleDateFormat.format(Date())
    val currentTime: String = simpleTimeFormat.format(Date())

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(25))
            .fillMaxWidth()
            .height(100.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFFF))
        ) {
            Text(
                text = "RTC - 1 fare",
                modifier = Modifier.fillMaxHeight(0.20f),
                fontSize = 10.sp,
            )
            Text(
                text = "Validity period",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),

                ) {

                Column(
                    modifier = Modifier.fillMaxWidth(0.50f)
                ) {
                    Text(
                        currentDT,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        currentTime.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        currentDT,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        currentTime.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun hello() {
    Text("hello")
}