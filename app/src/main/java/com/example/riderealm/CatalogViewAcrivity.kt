package com.example.riderealm

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CatalogViewAcrivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_view_acrivity)


        val fabWeb: FloatingActionButton = findViewById(R.id.openWebPage)
        fabWeb.setOnClickListener {
            val webPageUrl = "https://www.yamaha-motor-india.com/"
            openWebView(webPageUrl)
        }

        val fabQRAR: FloatingActionButton = findViewById(R.id.ARonQR)
        fabQRAR.setOnClickListener {

            val packageName = "com.DefaultCompany.AR_BikeWorld_Unity"

            if (isAppInstalled(packageName)) {
                val intent = packageManager.getLaunchIntentForPackage(packageName)
                startActivity(intent)
            } else {
                // The Unity app is not installed, show a Toast
                Toast.makeText(
                    this,
                    "Unity AR App is not available. Please install it from website.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val bike01: ImageView = findViewById(R.id.bike01)
        val bike02: ImageView = findViewById(R.id.bike02)
        val bike03: ImageView = findViewById(R.id.bike03)
        val scooter01: ImageView = findViewById(R.id.scooter01)
        val scooter02: ImageView = findViewById(R.id.scooter02)
        val scooter03: ImageView = findViewById(R.id.scooter03)

        bike01.setOnClickListener {
            openARView("Bike01")
        }
        bike02.setOnClickListener {
            openARView("Bike02")
        }
        bike03.setOnClickListener {
            openARView("Bike03")
        }
        scooter01.setOnClickListener {
            openARView("Scooter01")
        }
        scooter02.setOnClickListener {
            openARView("Scooter02")
        }
        scooter03.setOnClickListener {
            openARView("Scooter03")
        }
    }

    private fun openWebView(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("URL", url)
        startActivity(intent)
    }

    private fun openARView(clickedButton: String) {
        val intent = Intent(this, ARViewActivity::class.java)
        intent.putExtra("productName", clickedButton)
        startActivity(intent)
    }

    private fun isAppInstalled(packageName: String): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}