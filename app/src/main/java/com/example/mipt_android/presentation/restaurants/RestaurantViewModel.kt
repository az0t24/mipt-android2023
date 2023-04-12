package com.example.mipt_android.presentation.restaurants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mipt_android.data.repository.RestaurantRepository
import com.example.mipt_android.presentation.login.LoginEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel
@Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(RestaurantState())
    val uiState = _uiState.asStateFlow()

    fun onTriggerEvent(eventType: RestaurantEvent) {
//        when (eventType) {
//            is RestaurantEvent.getRestaurantList -> onGetRestaurantList()
//        }
    }

    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepository.fetchCatalog()
                .collectLatest { response ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            nearestRestaurants = response.nearest,
                            popularRestaurants = response.popular,
                        )
                    }
                }
        }
    }
}