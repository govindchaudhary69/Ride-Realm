package com.example.riderealm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val webView: WebView = findViewById(R.id.webView)

        val url = intent.getStringExtra("URL")
        if (url != null) {
            webView.loadUrl(url)
        } else {
            // Handle the case where no URL is provided
            // Inside your activity or fragment
            Toast.makeText(this, "No web url available.", Toast.LENGTH_SHORT).show()

        }
    }
}