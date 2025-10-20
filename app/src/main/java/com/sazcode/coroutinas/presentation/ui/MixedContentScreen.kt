package com.sazcode.coroutinas.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sazcode.coroutinas.presentation.model.ComeFromType
import com.sazcode.coroutinas.presentation.model.MixedContentUI
import com.sazcode.coroutinas.presentation.model.SectionUI
import com.sazcode.coroutinas.presentation.viewmodel.MixedContentScreenViewModel

@Composable
fun MixedContentScreenRoute(
    viewModel: MixedContentScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()

    MixedContentScreen(
        sections = uiState.sections
    )
}

@Composable
fun MixedContentScreen(sections: List<SectionUI>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 12.dp)
    ) {
        items(sections) { sectionUI ->
            MixedContentSection(
                sectionUI
            )
        }
    }
}

@Composable
fun MixedContentSection(section: SectionUI) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.65f)
                        )
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text(
                text = section.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        when {
            section.isLoading -> Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            section.error != null -> Text(
                text = section.error,
                color = MaterialTheme.colorScheme.error
            )

            else -> LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(section.items.chunked(2)) { rowItems ->
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        rowItems.forEach { character ->
                            SectionItemCard(character)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SectionItemCard(item: MixedContentUI) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(150.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            TitleAndValue(
                title = item.title,
                subTitle = item.subTitle
            )
        }
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


// SCREEN PREVIEW

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MixedContentSectionPreview() {
    val items = listOf(
        MixedContentUI(
            id = "1",
            title = "Goku",
            subTitle = "Race",
            image = "",
            comeFromType = ComeFromType.DRAGON_BALL
        ),
        MixedContentUI(
            id = "1",
            title = "Goku",
            subTitle = "Race",
            image = "",
            comeFromType = ComeFromType.DRAGON_BALL
        ),
        MixedContentUI(
            id = "1",
            title = "Goku",
            subTitle = "Race",
            image = "",
            comeFromType = ComeFromType.DRAGON_BALL
        ),
        MixedContentUI(
            id = "1",
            title = "Goku",
            subTitle = "Race",
            image = "",
            comeFromType = ComeFromType.DRAGON_BALL
        )
    )
    val sectionUI = SectionUI(
        id = "",
        isLoading = false,
        title = "Dragon ball z",
        items = items,
        error = null
    )
    Column {
        MixedContentSection(
            sectionUI
        )

        MixedContentSection(
            section = sectionUI
        )
    }
}