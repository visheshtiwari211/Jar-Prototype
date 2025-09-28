package com.example.jarprototype.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.jarprototype.ui.theme.viewmodel.JarViewModel

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    viewModel: JarViewModel = hiltViewModel<JarViewModel>()
) {
    val getEducationMetaDataflow by viewModel.getEducationMetaDataFlow.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getEducationMetaData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF713A65))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val cardLinearGradient =
            Brush.linearGradient(listOf(Color.White.copy(alpha = 0.2f), Color.White))
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
            getEducationMetaDataflow?.manualBuyEducationData?.educationCardList?.forEach { educationCard ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(shape = RoundedCornerShape(28.dp))
                        .background(color = Color(0xFF28085C).copy(alpha = 0.3f))
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(28.dp),
                            brush = cardLinearGradient
                        )
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
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
                }
            } ?: Log.e("OnBoardingScreen", "Education card list is null")

        }
    }
}