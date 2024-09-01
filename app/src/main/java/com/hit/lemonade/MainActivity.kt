package com.hit.lemonade

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hit.lemonade.ui.theme.LemonadeTheme
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeMakerApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeMakerApp() {
    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background,
        ) {
            when (step) {
                1 -> {
                    LemonTextAndImage(
                        R.string.desc_1,
                        R.drawable.lemon_tree,
                        R.string.image_desc_1,
                        onImageClicked = {
                            step = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }

                2 -> {
                    LemonTextAndImage(
                        R.string.desc_2,
                        R.drawable.lemon_squeeze,
                        R.string.image_desc_2,
                        onImageClicked = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                step = 3
                            }
                        }
                    )
                }

                3 -> {
                    LemonTextAndImage(
                        R.string.desc_3,
                        R.drawable.lemon_drink,
                        R.string.image_desc_3,
                        onImageClicked = { step = 4 }
                    )
                }

                else -> {
                    LemonTextAndImage(
                        R.string.desc_4,
                        R.drawable.lemon_restart,
                        R.string.image_desc_4,
                        onImageClicked = { step = 1 }
                    )
                }
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    desc: Int,
    image: Int,
    imageDesc: Int,
    onImageClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClicked,
                modifier = Modifier.wrapContentSize()
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = stringResource(imageDesc),
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = stringResource(desc))

        }
    }
}

@Preview
@Composable
fun LemonPreview() {
    LemonadeTheme () {
        LemonadeMakerApp()
    }
}