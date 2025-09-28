package com.example.jarprototype.ui.theme.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.jarprototype.model.EducationCard
import com.example.jarprototype.ui.theme.viewmodel.JarViewModel
import com.example.jarprototype.utils.Utils.toComposeColor
import kotlinx.coroutines.delay

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    viewModel: JarViewModel = hiltViewModel<JarViewModel>()
) {
    val screenHeight = LocalWindowInfo.current.containerSize
    val getEducationMetaDataflow by viewModel.getEducationMetaDataFlow.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getEducationMetaData()
    }
    val expandedIndexFlow by viewModel.expandedIndexFlow.collectAsStateWithLifecycle()
    var bgBrush by remember { mutableStateOf(Brush.linearGradient(listOf(Color.White, Color.White)))}

    LaunchedEffect(expandedIndexFlow) {
        if (expandedIndexFlow != null) {
            bgBrush = Brush.linearGradient(
                listOf(
                    getEducationMetaDataflow?.manualBuyEducationData?.educationCardList[expandedIndexFlow
                        ?: 0]?.startGradient?.toComposeColor() ?: Color.White,
                    getEducationMetaDataflow?.manualBuyEducationData?.educationCardList[expandedIndexFlow
                        ?: 0]?.endGradient?.toComposeColor() ?: Color.White
                )
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = bgBrush)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 15.5.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = 24.dp, width = 20.dp)
                        .padding(vertical = 2.dp),
                    tint = Color.White
                )

                Text(
                    text = "Onboarding",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color.White,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val size = getEducationMetaDataflow?.manualBuyEducationData?.educationCardList?.size ?: 0
            var expandedIndex by remember { mutableStateOf<Int?>(null) }
            getEducationMetaDataflow?.manualBuyEducationData?.educationCardList?.forEachIndexed { index, educationCard ->
                var visible by remember { mutableStateOf(false) }
                var expandedDuringAnimation by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    delay(index * 1500L)
                    visible = true
                    expandedDuringAnimation = true
                    expandedIndex = index
                    delay(1000L)
                    expandedDuringAnimation = false
                }

                LaunchedEffect(expandedIndex) {
                    viewModel.setExpandedIndex(expandedIndex)
                }

                AnimatedVisibility(
                    visible = visible,
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> screenHeight.height },
                        animationSpec = tween(
                            durationMillis = 1000,
                            easing = LinearOutSlowInEasing
                        )
                    ) + fadeIn(),
                    exit = fadeOut()
                ) {
                    val isExpanded =  index == expandedIndex || expandedDuringAnimation
                    EducationCard(
                        educationCard = educationCard,
                        expanded = isExpanded,
                        onExpandedStateToggle = {
                            if(expandedIndex != index) {
                                expandedIndex = index
                            }
                        }
                    )
                }
            } ?: Log.e("OnBoardingScreen", "Education card list is null")
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun EducationCard(
    modifier: Modifier = Modifier,
    educationCard: EducationCard,
    expanded: Boolean,
    onExpandedStateToggle: () -> Unit
) {
    val cardLinearGradient = Brush.linearGradient(
        listOf(
            educationCard.strokeStartColor.toComposeColor(),
            educationCard.strokeEndColor.toComposeColor()
        )
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = RoundedCornerShape(28.dp))
            .background(educationCard.backGroundColor.toComposeColor())
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(28.dp),
                brush = cardLinearGradient
            )
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        if (expanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable { onExpandedStateToggle() },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = educationCard.image,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                val formattedText = educationCard.expandStateText.replace(",", ",\n")
                Text(
                    text = formattedText,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        color = Color.White,
                        letterSpacing = 0.sp,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable { onExpandedStateToggle() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AsyncImage(
                        model = educationCard.image,
                        modifier = Modifier
                            .width(32.dp)
                            .height(36.dp)
                            .clip(shape = RoundedCornerShape(16.dp)),
                        contentDescription = null
                    )
                    Text(
                        text = educationCard.collapsedStateText,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight(700),
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            letterSpacing = 0.sp,
                            color = Color.White,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        )
                    )
                }
                Icon(
                    imageVector = Icons.Sharp.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFFBAB4CC)
                )
            }
        }
    }
}