package com.example.five.savedartworklist.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.five.R
import com.example.five.artworklist.domain.models.Artwork
import com.example.five.artworklist.presentation.ArtworkViewModel
import com.example.five.artworklist.presentation.ArtworksFragmentDirections
import com.example.five.artworklist.presentation.compose.AppBar
import com.example.five.artworklist.presentation.compose.ArtworkItem
import com.example.five.artworklist.presentation.compose.SearchMenu
import com.example.five.savedartworklist.presentation.ArtworksSavedFragmentDirections
import com.example.five.savedartworklist.presentation.SavedArtworkViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkSaved(
    artworksSaved: List<Artwork>, navController: NavController, artworkViewModel: ArtworkViewModel,
    savedArtworkViewModel: SavedArtworkViewModel
) {
    Column {
        AppBar("Избранное", navController)
        SearchMenu(artworkViewModel)
        Box(modifier = Modifier.background(Color.White)) {
            if (savedArtworkViewModel.isLoading.value == true) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 120.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                    Text(
                        text = "Загрузка...",
                        color = Color.Gray,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(
                            Font(R.font.inter_bold)
                        )
                    )
                }
            } else {
                if (artworksSaved.isNotEmpty()) {
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(artworksSaved) { item ->
                            ArtworkItem(artwork = item, savedArtworkViewModel) {
                                val action =
                                    ArtworksSavedFragmentDirections.actionArtworkSavedFragmentToArtworkDetailFragment(
                                        item
                                    )
                                navController.navigate(action)
                            }
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 120.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.bookmark_empty_saved),
                            contentDescription = "No artwork found",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 30.dp)
                        )
                        Text(
                            text = "У вас еще нет работ в избранном",
                            color = Color.Gray,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily(
                                Font(R.font.inter_bold)
                            )
                        )
                    }
                }
            }
        }
    }
}