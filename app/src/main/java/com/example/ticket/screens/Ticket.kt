package com.example.ticket.screens

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import android.view.MotionEvent
import androidx.compose.foundation.gestures.detectDragGestures

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ticket.R
import com.example.ticket.R.drawable.logo
import java.lang.Math.*
import java.lang.reflect.Array.get
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@SuppressLint("InvalidColorHexValue")
@Composable
fun Ticket(navController: NavHostController) {

    var isRotated by rememberSaveable { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isRotated) 360F else 0f,
        animationSpec = tween(durationMillis = 2500)
    )

    Image(painter = painterResource(R.drawable.drawing11),
        contentDescription ="",
        contentScale = ContentScale.Crop,
    modifier = Modifier.fillMaxSize())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .drawBehind { }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
                Back(modifier = Modifier)
                Back1(modifier = Modifier)
        }
        Box(contentAlignment = Alignment.Center)
        {

            Logo(Modifier.align(Alignment.Center))

            DraggableObject(caption = "d" )
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 8.dp)) {
            FirstCard(modifier = Modifier.weight(1f))
            SecondCard(modifier = Modifier.weight(1f))
        }
        Progress()
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
fun Back(modifier : Modifier) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(CircleShape)
            .size(35.dp)
            .background(Color(0xFFFFFFFF))
    ) {
        Image(
            ImageVector.vectorResource(id = R.drawable.back),
            "",
            modifier = Modifier.align(Alignment.Center),
            colorFilter = ColorFilter.tint(
                Color(0xFF000000),

                )
        )
    }
}

@Composable
fun Back1(modifier : Modifier) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(CircleShape)
            .size(35.dp)
            .background(Color(0xFFFFFFFF))
    ) {
        Image(
            ImageVector.vectorResource(id = R.drawable.ic_baseline_qr_code_scanner_24),
            "",
            modifier = Modifier.align(Alignment.Center),
            colorFilter = ColorFilter.tint(
                Color(0xFF000000),

                )
        )
    }
}

@Composable
fun QrCode(modifier : Modifier) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .padding(16.dp)
            .border(2.dp, Color.Gray, CircleShape)
            .size(35.dp)
            .background(Color(0xFFFFFFFF))
    ) {
        Image(
            ImageVector.vectorResource(id = R.drawable.ic_baseline_qr_code_scanner_24),
            "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.Center)
                .border(2.dp, Color.Gray, CircleShape)
                .clip(CircleShape),
            colorFilter = ColorFilter.tint(
                Color(0xFF000000),
            )
        )
    }
}

@Composable
fun FirstCard(modifier : Modifier) {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val simpleTimeFormat = SimpleDateFormat("hh:MM")

    val currentDT: String = simpleDateFormat.format(Date())
    val currentTime: String = simpleTimeFormat.format(Date())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 32.dp, 8.dp, 8.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(15))
            .height(130.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(15)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFFF))
        ) {
            Text(
                text = "RTC - 1 fare",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp, 0.dp, 0.dp, 16.dp)
                    .fillMaxHeight(0.20f),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold            )
            Text(
                text = "Validity period",
                modifier = Modifier.fillMaxHeight(0.350f),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
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
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp,
                    )
                    Text(
                        currentTime.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        currentDT,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp,
                    )
                    Text(
                        currentTime.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }

        }
    }
}

@Composable
fun SecondCard(modifier : Modifier) {

    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val simpleTimeFormat = SimpleDateFormat("hh:MM")
    val Lato = FontFamily(
        Font(R.font.overpass_extrabold),
    )
    val currentDT: String = simpleDateFormat.format(Date())
    val currentTime: String = simpleTimeFormat.format(Date())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp, 8.dp, 0.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(15))
            .height(130.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(15)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "For RTC only",
                modifier = Modifier.fillMaxHeight(0.20f),
                fontSize = 20.sp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.60f),

            ) {
                Image(painter = painterResource(R.drawable.newdude),
                    contentDescription ="",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxHeight(0.70f)
                        .fillMaxWidth(0.25f))
                Column(
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Text(
                        currentDT,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp,
                    )
                    Text(
                        currentTime.toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                    )
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.25f))
            }
            Text(
                text = "1-234FA2987BA4",
                modifier = Modifier.fillMaxHeight(0.99f),
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp
            )
        }
    }
}

@Composable
fun Logo(modifier : Modifier) {
    Card(
        modifier = Modifier
            .clip(CircleShape)
            .size(150.dp)
            .border(1.dp, Color.Black, shape = CircleShape)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color(0xFFFFFFFFF))
        ) {
            Image(
                painter = painterResource(R.drawable.logo55),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

    }
}
@Composable
fun Spinning(modifier : Modifier) {
    var isRotated by rememberSaveable { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isRotated) 360F else 0f,
        animationSpec = tween(durationMillis = 2500)
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .rotate(rotationAngle)
            .clip(CircleShape),

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                )
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.Blue)
                )
            }
        }
    }
}
@Preview
@Composable
fun Progress() {
    LinearProgressIndicator(
        progress = 0.1f,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 32.dp)
            .clip(RoundedCornerShape(50))
            .height(10.dp),
        backgroundColor = Color.Black,
        color = Color(0xFF13C642) //progress color)
    )
}

@Composable
private fun AnimateAsFloatContent(isTouched : Boolean
) {
    var duration by rememberSaveable {mutableStateOf(3000)}

        val rotationAngle = infiniteRepeatable<Float>(
        animation = tween(
            durationMillis = 5000,
            easing = LinearEasing,
        )
    )


    val xRotation by animateValues(
        values = listOf(0f, 180f, 180f, 0f, 0f),
        animationSpec = rotationAngle
    )
    val yRotation by animateValues(
        values = listOf(0f, 0f, 180f, 180f, 0f),
        animationSpec = rotationAngle
    )
    val zRotation by animateValues(
        values = listOf(-360f,360f),
        animationSpec = rotationAngle
    )
    val zFastRotation by animateValues(
        values = listOf(-500f,0f),
        animationSpec = rotationAngle
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var rotation : Float = 0f
        Box(modifier = Modifier
            .graphicsLayer {
                if(isTouched){rotationZ = zRotation} else {rotationZ = zFastRotation}
            }
        ) {
            Spinning(modifier = Modifier.graphicsLayer {  })
        }
    }}

@Composable
fun animateValues(
    values: List<Float>,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Float> {
    // 1. Create the groups zipping with next entry
    val groups by rememberUpdatedState(newValue = values.zipWithNext())
    // 2. Start the state with the first value
    val state = remember { mutableStateOf(values.first()) }
    LaunchedEffect(key1 = groups) {
        val (_, setValue) = state
        // Start the animation from 0 to groups quantity
        animate(
            initialValue = 0f,
            targetValue = groups.size.toFloat(),
            animationSpec = animationSpec,
        ) { frame, _ ->
            // Get which group is being evaluated
            val integerPart = frame.toInt()
            val (initialValue, finalValue) = groups[frame.toInt()]
            // Get the current "position" from the group animation
            val decimalPart = frame - integerPart
            // Calculate the progress between the initial and final value
            setValue(
                initialValue + (finalValue - initialValue) * decimalPart
            )
        }
    }
    return state
}
@Composable
fun DraggableObject(caption: String){
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var isTouched : Boolean = false
    Box(
        Modifier
            .size(250.dp)
            .offset {
                IntOffset(
                    x = offsetX.roundToInt(),
                    y = offsetY.roundToInt()
                )
            }
            .pointerInput(Unit) {
                detectDragGestures (
                    onDrag = { change, dragAmount ->
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                        isTouched == true
                    },
                onDragEnd = {
                    offsetX -= offsetX
                    offsetY -= offsetY
                }
                )
            }
            .clip(CircleShape)
            .padding(25.dp)
    ){
        AnimateAsFloatContent(isTouched = isTouched)
    }
}