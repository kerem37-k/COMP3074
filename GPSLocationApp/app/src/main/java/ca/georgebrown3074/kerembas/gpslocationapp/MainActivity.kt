
package ca.georgebrown3074.kerembas.gpslocationapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var txtLatitude: TextView
    private lateinit var txtLongitude: TextView

    private val locationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                getLocation()
            } else {
                Toast.makeText(
                    this,
                    "Precise location permission is required for GPS",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtLatitude = findViewById(R.id.txtLatitude)
        txtLongitude = findViewById(R.id.txtLongitude)

        val btnLocation = findViewById<Button>(R.id.btnLocation)

        btnLocation.setOnClickListener {
            checkLocationPermission()
        }
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else {
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun getLocation() {
        val locationManager =
            getSystemService(LOCATION_SERVICE) as LocationManager

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "Please enable GPS", Toast.LENGTH_LONG).show()
            return
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        txtLatitude.text = "Latitude: Getting GPS location..."
        txtLongitude.text = "Longitude: Getting GPS location..."

        locationManager.getCurrentLocation(
            LocationManager.GPS_PROVIDER,
            null,
            mainExecutor
        ) { location ->
            if (location != null) {
                txtLatitude.text = "Latitude: ${location.latitude}"
                txtLongitude.text = "Longitude: ${location.longitude}"
            } else {
                txtLatitude.text = "Latitude: Unavailable"
                txtLongitude.text = "Longitude: Unavailable"
                Toast.makeText(
                    this,
                    "GPS location unavailable. Try again.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
