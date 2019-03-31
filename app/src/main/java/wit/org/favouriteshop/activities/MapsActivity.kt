package wit.org.favouriteshop.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import wit.org.favouriteshop.R
import wit.org.favouriteshop.models.Location

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener {

    private lateinit var Map: GoogleMap
    var location = Location()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        location = intent.extras.getParcelable<Location>("location")
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMarkerClick(marker: Marker): Boolean {
        val loc = LatLng(location.lat, location.lng)
        marker.setSnippet("GPS : " + loc.toString())
        return false
    }
    override fun onMapReady(googleMap: GoogleMap) {
        Map = googleMap
        val loc = LatLng(location.lat, location.lng)
        val options = MarkerOptions()
            .title("Shop")
            .snippet("GPS : " + loc.toString())
            .draggable(true)
            .position(loc)
        Map.setOnMarkerDragListener(this)
        Map.setOnMarkerClickListener(this)
        Map.addMarker(options)
        Map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, location.zoom))
    }
    override fun onMarkerDragStart(marker: Marker) {
    }

    override fun onMarkerDrag(marker: Marker) {
    }

    override fun onMarkerDragEnd(marker: Marker) {
        location.lat = marker.position.latitude
        location.lng = marker.position.longitude
        location.zoom = Map.cameraPosition.zoom

        Toast.makeText(applicationContext, "Lat ${location.lat} Lng ${location.lng}", Toast.LENGTH_LONG).show()
    }
    override fun onBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra("location", location)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
        super.onBackPressed()
    }

}
