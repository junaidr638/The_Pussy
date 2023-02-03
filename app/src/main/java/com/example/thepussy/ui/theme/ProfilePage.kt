package com.example.thepussy.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.thepussy.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProfiePage() {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        Card(
            elevation = 6.dp, modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, bottom = 30.dp, start = 2.dp, end = 2.dp)
                .border(4.dp, Color.Blue, RoundedCornerShape(20.dp))
        ) {


            BoxWithConstraints() {
                val constraints = if (minWidth < 600.dp) {
                    portraitConstraints(margin = 16.dp)
                } else {
                    landscapeConstraints(margin = 16.dp)
                }

                ConstraintLayout(constraints,
                    modifier = Modifier.layoutId("constraintLayout")) {
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
                            )
                            .layoutId("Image"),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = "Persian Cat",
                        modifier = Modifier.layoutId("catBreed")
                    )
                    Text(
                        text = "Iran",
                        modifier = Modifier.layoutId("country")
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(80.dp), modifier = Modifier
                            .padding(16.dp)
                            .layoutId("rowStats")
                    ) {

                        ProfileStats(count = "200", section = "Followers")
                        ProfileStats(count = "150", section = "Following")
                        ProfileStats(count = "346", section = "Posts")


                    }


                    Button(onClick = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Added to Followers",
                                actionLabel = "Undo",
                                duration = SnackbarDuration.Long
                            )
                        }
                    }, modifier = Modifier.layoutId("followButton")) {
                        Text(text = "Follow")
                    }

                    Button(onClick = {
                        coroutineScope.launch {
                            val snackResult = scaffoldState.snackbarHostState.showSnackbar(
                                message = "A message will be sent soon",
                                actionLabel = "Undo",
                                duration = SnackbarDuration.Long
                            )

                            when (snackResult) {
                                SnackbarResult.ActionPerformed -> {}
                                SnackbarResult.Dismissed -> {}
                            }


                        }
                    }, modifier = Modifier.layoutId("messageButton")) {
                        Text(text = "Message")
                    }


                }
            }
        }

    }
}


 fun portraitConstraints(margin: Dp): ConstraintSet {

    return ConstraintSet {
        val image = createRefFor("Image")
        val catBreed = createRefFor("catBreed")
        val country = createRefFor("country")
        val rowStats = createRefFor("rowStats")
        val followButton = createRefFor("followButton")
        val messageButton = createRefFor("messageButton")
        val guideline = createGuidelineFromTop(0.3f)

        constrain(image){
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(catBreed){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
         constrain(country){
             top.linkTo(catBreed.bottom)
             start.linkTo(parent.start)
             end.linkTo(parent.end)
         }
        constrain(rowStats){
            top.linkTo(country.bottom)
        }

        constrain(followButton){
            top.linkTo(rowStats.bottom,margin = margin)
            start.linkTo(parent.start)
            end.linkTo(messageButton.start)
            width = Dimension.wrapContent
        }

        constrain(messageButton){
            top.linkTo(rowStats.bottom,margin = margin)
            start.linkTo(followButton.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }
    }

}



fun landscapeConstraints(margin: Dp): ConstraintSet {

    return ConstraintSet {
        val image = createRefFor("Image")
        val catBreed = createRefFor("catBreed")
        val country = createRefFor("country")
        val rowStats = createRefFor("rowStats")
        val followButton = createRefFor("followButton")
        val messageButton = createRefFor("messageButton")
        val guideline = createGuidelineFromTop(0.3f)
        val constraintLayout = createRefFor("constraintLayout")

        constrain(image){
            top.linkTo(parent.top,margin = margin)
            start.linkTo(parent.start,margin = margin)
        }

        constrain(catBreed){
            start.linkTo(image.start)
            top.linkTo(image.bottom)
            end.linkTo(image.end)
        }
        constrain(country){
            top.linkTo(catBreed.bottom)
            start.linkTo(catBreed.start)
            end.linkTo(catBreed.end)
        }
        constrain(rowStats){
           start.linkTo(image.end,margin = margin)
            end.linkTo(constraintLayout.end)
            top.linkTo(image.top)
        }

        constrain(followButton){
            top.linkTo(rowStats.bottom,margin = margin)
            start.linkTo(rowStats.start)
            end.linkTo(messageButton.start)
            width = Dimension.wrapContent
        }

        constrain(messageButton){
            top.linkTo(rowStats.bottom,margin = margin)
            start.linkTo(followButton.end)
            end.linkTo(rowStats.end)

            width = Dimension.wrapContent
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

   @Preview
   @Composable
   fun MyPreview(){
       ProfiePage()
   }