package com.example.riderealm

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openAppButton: Button = findViewById(R.id.catalog)
        openAppButton.setOnClickListener {
            openCatalog()
        }

        val fabWeb: FloatingActionButton = findViewById(R.id.webPage)
        fabWeb.setOnClickListener {
            val webPageUrl = "https://github.com/Sukarnascience/AR_Bike_World"
            openWebView(webPageUrl)
        }

        val forQRAR: Button = findViewById(R.id.unityOpenBTN)
        forQRAR.setOnClickListener {
            val packageName = "com.DefaultCompany.AR_BikeWorld_Unity"

            if (isAppInstalled(packageName)) {
                val intent = packageManager.getLaunchIntentForPackage(packageName)
                startActivity(intent)
            } else {
                // The Unity app is not installed, show a Toast
                Toast.makeText(
                    this,
                    "Unity AR App is not available. Please install it from Website.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }


    private fun isAppInstalled(packageName: String): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun openWebView(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("URL", url)
        startActivity(intent)
    }

    private fun openCatalog() {
        val intent = Intent(this, CatalogViewAcrivity::class.java)
        intent.putExtra("productName", "passmein")
        startActivity(intent)
    }

}