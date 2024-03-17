package com.example.five.artworklist.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.five.R
import com.example.five.artworklist.domain.models.Artwork
import com.example.five.artworklist.presentation.ArtworkViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun  ArtworkItem(
    artwork: Artwork,
    viewModel: ArtworkViewModel,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.margin_start_end_content),
                vertical = dimensionResource(id = R.dimen.margin_start_vertical_content)
            )
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.radius_card_artwork)))
            .background(Color.White)
            .clickable(onClick = onClick)
    ) {
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
            ), elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(id = R.dimen.elevation_card)
            ), modifier = Modifier.fillMaxSize()
        ) {
            GlideImage(
                modifier = Modifier
                    .size(
                        height = dimensionResource(id = R.dimen.height_img_exhibit),
                        width = dimensionResource(id = R.dimen.width_img_exhibit)
                    )
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner_shape_img)))
                    .fillMaxWidth(),
                model = "https://www.artic.edu/iiif/2/${artwork.imageId}/full/843,/0/default.jpg",
                contentScale = ContentScale.FillBounds,
                contentDescription = "image",
            )
            Row(
                modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.margin_bottom_category),
                            bottom = dimensionResource(id = R.dimen.margin_bottom_column)
                        )
                        .weight(2f)
                ) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_one)))
                    if (!artwork.title.isNullOrEmpty()) {
                        Text(
                            text = artwork.title,
                            fontSize = 20.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = FontFamily(Font(R.font.inter_regular))
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_two)))
                    if (!artwork.dateDisplay.isNullOrEmpty()) {
                        Text(
                            text = artwork.dateDisplay,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = FontFamily(Font(R.font.inter_regular))
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_three)))
                    if (!artwork.artistTitle.isNullOrEmpty()) {
                        Text(
                            text = artwork.artistTitle,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = FontFamily(Font(R.font.inter_regular))
                        )
                    }
                }
                Image(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_image))
                        .weight(1f)
                        .clickable {viewModel.toggleSavedArtwork(artwork)},
                    painter = if (artwork.isBookmarked) {
                        painterResource(id = R.drawable.bookmark_red_small)
                    } else {
                        //код, если Artwork не является закладкой
                        painterResource(id = R.drawable.bookmark)
                    },
                    contentDescription = if (artwork.isBookmarked) {
                        //код, если Artwork является закладкой
                        "Bookmarked"
                    } else {
                        //код, если Artwork не является закладкой
                        "Not bookmarked"
                    }
                )
            }
        }
    }
}