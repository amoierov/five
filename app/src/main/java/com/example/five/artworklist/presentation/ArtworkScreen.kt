package com.example.five.artworklist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.five.R
import com.example.five.artworklist.domain.models.Artwork


@Composable
fun AppBar(name: String, navController: NavController) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color(R.color.purple)) // Устанавливаем цвет пурпурного фона
                .padding(
                    vertical = dimensionResource(id = R.dimen.margin_top_card),
                    horizontal = dimensionResource(id = R.dimen.margin_bottom_column)
                )
        ) {
            // Иконка "Назад"
            Image(
                painter = painterResource(id = R.drawable.arrow_1),
                contentDescription = "Arrow Back",
                modifier = Modifier
                    .clickable {
                        navController.navigate(R.id.action_exhibitsFragment_to_yourFragment)
                    }
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_18dp))) // Пространство между иконкой и текстом

            // Текст в верхнем баре
            Text(
                text = name,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.open_sans_regular)),
                modifier = Modifier.wrapContentWidth()
            )
        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun SearchMenu(viewModel: ArtworkViewModel) {

    val searchText = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        SearchBar(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_search_start),
                    top = dimensionResource(id = R.dimen.padding_search_top),
                    end = dimensionResource(id = R.dimen.padding_search_end),
                    bottom = dimensionResource(id = R.dimen.padding_search_bottom)
                )
                .fillMaxWidth()
                .shadow(
                    elevation = dimensionResource(id = R.dimen.elevation_shadow),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.radius_card)),
                    clip = false
                ),
            query = searchText.value,
            onQueryChange = { text ->
                searchText.value = text
                if (text.length >= 2) {
                    viewModel.filterArtworks(text)
                } else {
                    viewModel.restoreOriginalArtworks()
                }

            },
            onSearch = {},
            active = false,
            colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.onSecondary),
            onActiveChange = {},
            shape = RoundedCornerShape(10.dp),
            placeholder = {
                Text(
                    text = "Введите название категории",
                    fontFamily = FontFamily(Font(R.font.open_sans_regular)), fontSize = 20.sp
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.group_8),
                    contentDescription = "Search",
                )
            },
        ) {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkScreen(
    artworks: List<Artwork>,
    nameCategory: String,
    navController: NavController,
    viewModel: ArtworkViewModel
) {
    Column {
        AppBar(nameCategory, navController)
        SearchMenu(viewModel)
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(artworks) { item ->
                ArtworkItem(artwork = item)
            }
        }
    }

}

