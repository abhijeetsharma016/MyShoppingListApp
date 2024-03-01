package com.example.shoppinglistapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {
    private  val _location= mutableStateOf<LocationData?>(null)
    val location: MutableState<LocationData?> = _location


    fun updateLocation(newLocation: LocationData){
        _location.value = newLocation
    }
}