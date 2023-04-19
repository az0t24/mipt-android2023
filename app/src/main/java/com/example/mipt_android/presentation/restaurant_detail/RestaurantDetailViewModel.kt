package com.example.mipt_android.presentation.restaurant_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mipt_android.data.model.remote.RemoteRestaurant
import com.example.mipt_android.data.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel
@Inject constructor(
    private val restaurantRepository: RestaurantRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RestaurantDetailState(RemoteRestaurant()))
    val uiState = _uiState.asStateFlow()

    fun onTriggerEvent(eventType: RestaurantDetailEvent) {
        when(eventType) {
            is RestaurantDetailEvent.getRestaurant -> getRestaurant(eventType.id)
        }
    }

    private fun getRestaurant(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepository.getRestaurant(id)
                .collectLatest { response ->
                    _uiState.update {currentState ->
                        currentState.copy(
                            restaurant = response
                        )
                    }
                }
        }
    }
}