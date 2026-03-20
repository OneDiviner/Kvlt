package com.example.kvlt.navigation.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.impl.presentation.CollapsedPlayerView
import kvlt.core.resources.generated.resources.Res
import kvlt.core.resources.generated.resources.collection_icon
import kvlt.core.resources.generated.resources.history_icon
import kvlt.core.resources.generated.resources.home_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {

                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(Res.drawable.home_icon),
                        contentDescription = "home_icon",
                    )
                    Text(
                        modifier = Modifier,
                        text = "Главная",
                        style = TextStyle(
                            fontSize = 12.sp,
                            letterSpacing = 0.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {

                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(Res.drawable.collection_icon),
                        contentDescription = "collection_icon",
                    )
                    Text(
                        modifier = Modifier,
                        text = "Коллекция",
                        style = TextStyle(
                            fontSize = 12.sp,
                            letterSpacing = 0.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {

                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(Res.drawable.history_icon),
                        contentDescription = "history_icon",
                    )
                    Text(
                        modifier = Modifier,
                        text = "История",
                        style = TextStyle(
                            fontSize = 12.sp,
                            letterSpacing = 0.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}