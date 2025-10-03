package com.sazcode.coroutinas.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sazcode.coroutinas.presentation.model.DragonBallCharacterUI
import com.sazcode.coroutinas.presentation.state.MainScreenState
import com.sazcode.coroutinas.presentation.viewmodel.DragonBallCharactersViewModel

@Composable
fun MainCoverageInformationRoute(
    viewModel: DragonBallCharactersViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()

    CoverageInformationScreen(
        uiState = uiState
    )
}

@Composable
fun CoverageInformationScreen(
    uiState: MainScreenState
) {
    SectionContainer(
        uiState = uiState,
    )
}

@Composable
fun SectionContainer(uiState: MainScreenState) {
    if (uiState.isDragonBallSectionLoading) {
        LoadingOverlay()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(uiState.dragonBallCharacters) { item ->
                SectionItem(item)
            }
        }
    }
}

@Composable
fun SectionItem(item: DragonBallCharacterUI) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .padding(8.dp) ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = item.image,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(8.dp))

        TitleAndValue(
            title = item.name,
            subTitle = item.race
        )

    }
}

@Composable
fun TitleAndValue(
    title: String,
    subTitle: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = subTitle,
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray
        )
    }
}

@Composable
fun LoadingOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}