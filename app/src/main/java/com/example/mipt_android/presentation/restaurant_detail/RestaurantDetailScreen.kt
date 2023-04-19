package com.example.mipt_android.presentation.restaurant_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.mipt_android.data.model.remote.RemoteRestaurant

@Composable
fun RestaurantDetailScreen(
    restaurantId: String,
    viewModel: RestaurantDetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState) {
        viewModel.onTriggerEvent(RestaurantDetailEvent.getRestaurant(restaurantId.toInt()))
    }

    RestaurantCard(restaurant = uiState.value.restaurant)
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