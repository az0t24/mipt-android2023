package com.example.mipt_android.presentation.restaurants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.mipt_android.R
import com.example.mipt_android.data.model.remote.RemoteRestaurant

@Composable
fun RestaurantScreen(
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            content = {
                uiState.value.nearestRestaurants.forEach {
                    item {
                        RestaurantCard(restaurant = it)
                    }
                }
                uiState.value.popularRestaurants.forEach {
                    item {
                        RestaurantCard(restaurant = it)
                    }
                }
            },
            contentPadding = PaddingValues(horizontal = 30.dp)
        )
    }
}

@Composable
fun RestaurantCard(
    restaurant: RemoteRestaurant,
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(restaurant.image)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = "${restaurant.name} logo"
            )

            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = restaurant.name,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                maxLines = 1,
            )

            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = restaurant.deliveryTime,
                fontSize = 12.sp,
                color = Color.Gray,
                maxLines = 1,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantScreenPreview() {
    RestaurantScreen()
}