package com.example.myshoppinglistapp


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

class LocationUtils(val context:Context) {

    private val _fusedLocationClient: FusedLocationProviderClient //Initial pointer which will fuse the location so that we can work with it
            = LocationServices.getFusedLocationProviderClient(context)


    @SuppressLint("MissingPermission")//make sure u only call this function if u have permission
    fun requestLocationUpdates(viewModel: LocationViewModel) {
        val locationCallback = object : LocationCallback() { //call back is something which you send
            // data and then wait for it to process data and give you callback
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let {
                    val location = LocationData(latitude = it.latitude, longitude = it.longitude)
                    viewModel.updateLocation(location)
                }
            }
        }
//above code is just a callback it wont do anything unless we ask it ro, now asking it for the same
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 1000
        ).build()//will consume high battery

        _fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )//Looper controls the thread the project runs on
    }


    fun haslocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                &&
                return ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

    }

    fun reverseGeocodeLocation(location: LocationData): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val coordinate = LatLng(location.latitude, location.longitude)
        val addresses: MutableList<Address>? =
            geocoder.getFromLocation(coordinate.latitude, coordinate.longitude, 1)
        return if (addresses?.isNotEmpty() == true) {
            addresses[0].getAddressLine(0)
        } else {
            "Address not found"
        }
    }
}