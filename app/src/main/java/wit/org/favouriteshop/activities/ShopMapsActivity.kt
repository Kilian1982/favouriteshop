package wit.org.favouriteshop.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_shop_maps.*
import wit.org.favouriteshop.R
import kotlinx.android.synthetic.main.content_shop_maps.*
import wit.org.favouriteshop.main.MainApp

class ShopMapsActivity: AppCompatActivity(), GoogleMap.OnMarkerClickListener{
    lateinit var app: MainApp
    lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp
        setContentView(R.layout.activity_shop_maps)
        setSupportActionBar(toolbarMaps)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync {
            map = it
            configureMap()
        }
    }
        override fun onDestroy() {
            super.onDestroy()
            mapView.onDestroy()
        }

        override fun onLowMemory() {
            super.onLowMemory()
            mapView.onLowMemory()
        }

        override fun onPause() {
            super.onPause()
            mapView.onPause()
        }

        override fun onResume() {
            super.onResume()
            mapView.onResume()
        }

        override fun onSaveInstanceState(outState: Bundle?) {
            super.onSaveInstanceState(outState)
            mapView.onSaveInstanceState(outState)
        }
        fun configureMap() {
        map.uiSettings.setZoomControlsEnabled(true)
        app.shops.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options).tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
            map.setOnMarkerClickListener(this)
        }
    }
        override fun onMarkerClick(marker: Marker): Boolean {
            currentTitle.text = marker.title
            //trying to add the description as well
            //currentDescription.text = marker.description
            return false
        }

    }

