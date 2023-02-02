package com.example.thepussy.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.thepussy.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfiePage() {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(modifier = Modifier.fillMaxSize(),scaffoldState = scaffoldState) {
        Card(elevation = 6.dp, modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp, start = 2.dp, end = 2.dp)
            .border(4.dp, Color.Blue, RectangleShape)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.jeremy),
                    contentDescription = "Persian Cat",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(
                            width = 3.dp,
                            color = Color.Green,
                            shape = CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )

                Text(text = "Persian Cat")
                Text(text = "Iran")

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {

                    ProfileStats(count = "200", section = "Followers")
                    ProfileStats(count = "150", section = "Following")
                    ProfileStats(count = "346", section = "Posts")


                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {


                    Button(onClick = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Added to Followers",
                                actionLabel = "Undo",
                                duration = SnackbarDuration.Indefinite
                            )
                        }
                    }) {
                        Text(text = "Follow")
                    }

                    Button(onClick = {  coroutineScope.launch {
                        val snackResult =  scaffoldState.snackbarHostState.showSnackbar(
                            message = "A message will be sent soon",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Long
                        )

                        when(snackResult){
                            SnackbarResult.ActionPerformed -> {}
                            SnackbarResult.Dismissed -> {}
                        }


                    } }) {
                        Text(text = "Message")
                    }


                }
            }
        }
    }
}


@Composable
fun ProfileStats(count: String, section: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.ExtraBold)
        Text(text = section)
    }
}