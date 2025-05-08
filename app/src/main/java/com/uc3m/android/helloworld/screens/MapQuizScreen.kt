package com.uc3m.android.helloworld.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.uc3m.android.helloworld.data.Question
import com.uc3m.android.helloworld.data.QuestionType
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapQuizScreen(
    question: Question,
    modifier: Modifier = Modifier,
    onRegionTapped: (String) -> Unit
) {
    // theme colors
    val orange    = Color(0xFFFF9966)
    val white     = Color.White
    val lightGray = Color(0xFFF5F5F5)

    // grab context once
    val context = LocalContext.current

    // preload the raw GeoJSON string so we don't call a @Composable inside AndroidView callback
    val rawGeoJson by remember(question.geoJsonAsset) {
        mutableStateOf(
            context.assets.open(question.geoJsonAsset!!)
                .bufferedReader()
                .use { it.readText() }
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(lightGray)
            .padding(16.dp)
    ) {
        Text(
            text = question.questionText,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(12.dp))

        // tie MapView to Compose lifecycle
        val mapView = rememberMapViewWithLifecycle()

        AndroidView(
            factory = { mapView },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) { mv ->
            mv.getMapAsync { gMap ->
                gMap.clear()

                // parse & add GeoJSON layer
                val layer = GeoJsonLayer(gMap, JSONObject(rawGeoJson))
                layer.addLayerToMap()

                // build LatLngBounds to center camera
                val boundsBuilder = LatLngBounds.Builder()
                for (feature in layer.features) {
                    val geom = feature.geometry
                    if (geom is com.google.maps.android.data.geojson.GeoJsonPolygon) {
                        geom.coordinates.flatten().forEach { coord ->
                            boundsBuilder.include(coord)
                        }
                    }
                }
                gMap.moveCamera(
                    CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 50)
                )

                // when the user taps a region...
                layer.setOnFeatureClickListener { feature ->
                    val pickedName = feature.getProperty(question.regionProperty)
                    onRegionTapped(pickedName)
                    Toast.makeText(
                        mv.context,
                        if (pickedName == question.correctAnswer.toString()) "Correct!" else "Wrong: it was ${question.correctAnswer}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}


/** Helper to tie MapView into Compose lifecycle */
@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context   = LocalContext.current
    val mapView   = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE  -> mapView.onCreate(null)
                Lifecycle.Event.ON_START   -> mapView.onStart()
                Lifecycle.Event.ON_RESUME  -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE   -> mapView.onPause()
                Lifecycle.Event.ON_STOP    -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else                       -> {}
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }

    return mapView
}
