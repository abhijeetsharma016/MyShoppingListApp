package com.example.shoppinglistapp

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {
    private  val _location= mutableStateOf<LocationData?>(null)
    val location: MutableState<LocationData?> = _location

    private val _address = mutableStateOf(listOf<GeocodingResult>())
    val address: State<List<GeocodingResult>> = _address
    fun updateLocation(newLocation: LocationData){
        _location.value = newLocation
    }


    fun fetchAddress(latlng: String){
        try{
            viewModelScope.launch {
                val result = RetrofitClient.create().getAddressFromCoordinates(
                    latlng,
                    //TODO this key is deleted make your own key(also change at manifest)
                    "AIzaSyBnDotQg3DKXFiLZPiQD6ssOYZoNHdYodk"
                )
                _address.value = result.results
            }
        }catch(e:Exception) {
            Log.d("res1", "${e.cause} ${e.message}")
        }
    }
}