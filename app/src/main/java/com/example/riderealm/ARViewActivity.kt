package com.example.riderealm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode

class ARViewActivity : AppCompatActivity() {

    private lateinit var modelName: String
    lateinit var sceneView: ArSceneView
    lateinit var placeButton: ExtendedFloatingActionButton
    lateinit var modelNode: ArModelNode

    lateinit var modelInfoTextView: TextView

    private var isFragmentVisible = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arview)

        sceneView = findViewById(R.id.sceneView) // Initialize sceneView here

        // Retrieve the clicked button information from the intent
        val whichProduct = intent.getStringExtra("productName")
        //openAR(modelName)
        if (whichProduct != null) {
            modelName = whichProduct
            // Inside your activity or fragment
            Toast.makeText(this, modelName, Toast.LENGTH_SHORT).show()
            openAR(modelName)
        } else {
            openAR("Bike01")
        }

        val fab: FloatingActionButton = findViewById(R.id.productInfoBtn)
        fab.setOnClickListener {

            val webPageUrl: String = when (whichProduct) {
                "Bike01" -> "https://www.yamaha-motor-india.com/yamaha-r15v4.html"
                "Bike02" -> "https://www.yamaha-motor-india.com/yamaha-fzs-fi-v4.html"
                "Bike03" -> "https://www.yamaha-motor-india.com/yamaha-mt-15-v2.html"
                "Scooter01" -> "https://www.yamaha-motor-india.com/yamaha-fascino125fi-new.html"
                "Scooter02" -> "https://www.yamaha-motor-india.com/yamaha-ray-zrstreetrally125fihybrid.html"
                "Scooter03" -> "https://www.yamaha-motor-india.com/yamaha-ray-zr125fihybrid.html"
                else -> "https://www.yamaha-motor-india.com/"
            }

            openWebView(webPageUrl)
        }

        val fabFragment: FloatingActionButton = findViewById(R.id.fragData)
        fabFragment.setOnClickListener {
            showToastBasedOnModel(whichProduct)
        }
    }

    private fun openWebView(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("URL", url)
        startActivity(intent)
    }

    private fun openAR(product: String){
        val modelName: String = when (product) {
            "Bike01" -> "models/sports_bike04.glb"
            "Bike02" -> "models/cyberpunk_bike03.glb"
            "Bike03" -> "models/2020_suzuki_sv650_motorcycle02.glb"
            "Scooter01" -> "models/vespa06.glb"
            "Scooter02" -> "models/vespa_cartoonish_lineart05.glb"
            "Scooter03" -> "models/2020_suzuki_sv650_motorcycle01.glb"
            else -> "models/cyberpunk_bike03.glb"
        }

        sceneView = findViewById(R.id.sceneView)
        placeButton = findViewById(R.id.place)

        placeButton.setOnClickListener {
            placeModel()
        }

        modelNode = ArModelNode().apply {
            loadModelGlbAsync(
                glbFileLocation = modelName
            ){
                sceneView.planeRenderer.isVisible = true
            }
            onAnchorChanged = {
                placeButton.isGone
            }
        }
        sceneView.addChild(modelNode)
    }

    private fun placeModel() {
        modelNode.anchor()
        sceneView.planeRenderer.isVisible = false
    }

    // Function to show Toast based on the model
    @SuppressLint("MissingInflatedId")
    private fun showToastBasedOnModel(modelName: String?) {
        val toastMessage = when (modelName) {
            "Bike01" -> "Name: R15 V4\n" +
                    "Engine type: Liquid-cooled, 4-stroke, SOHC, 4-valve\n" +
                    "Displacement: 155 CC\n" +
                    "Maximum horse power: 13.5kW(18.4PS)/10000 RPM\n" +
                    "Starting system type: Electric starter\n" +
                    "Maximum torque: 14.2 Nm (1.4 kgfm) @7,500 RPM\n" +
                    "Price: INR1 82 000*"
            "Bike02" -> "Name: FZ-S FI Ver 4.0 DLX\n" +
                    "Engine Type: Air cooled, 4-stroke, SOHC, 2-valve\n" +
                    "Displacement: 149 cc\n" +
                    "Maximum Horse Power: 9.1 kW (12.4PS) / 7,250 r/min\n" +
                    "Starting system type: Electric Starter\n" +
                    "Maximum torque: 13.3 N.m (1.4 kg f.m) / 5,500 r/min\n" +
                    "Price: INR 1,29,700*"
            "Bike03" -> "Name: MT-15 V2\n" +
                    "Engine type: Liquid cooled, 4-stroke, SOHC, 4-valve\n" +
                    "Displacement: 155cc\n" +
                    "Maximum torque: 14.1N.m(1.4kgf.m)/7500r/min\n" +
                    "Starting system type: Electric starter\n" +
                    "Maximum torque: 14.1N.m(1.4kgf.m)/7500r/min\n" +
                    "Price: INR 1,67,700*"
            "Scooter01" -> "Name: Fascino 125 Fi Hybrid\n" +
                    "Engine Type: Air cooled, 4-stroke,SOHC, 2-valve\n" +
                    "Displacement: 125 cc\n" +
                    "Maximum Torque (With Assist Function Working): 10.3 N.m(1.1kgf.m)/5000 r/min\n" +
                    "Starting System Type: Electric starter and kick starter\n" +
                    "Maximum Horse Power: 6.0kW(8.2PS)/6500r/min\n" +
                    "Price: INR 79,600*"
            "Scooter02" -> "Name: RayZR Street Rally 125 Fi Hybrid\n" +
                    "Engine Type: Air cooled, 4-stroke,SOHC, 2-valve\n" +
                    "Displacement: 125 cc\n" +
                    "Maximum Torque (With Assist Function Working): 10.3 N.m(1.1kgf.m)/5000 r/min\n" +
                    "Starting System Type: Electric starter and kick starter\n" +
                    "Maximum Horse Power: 6.0kW(8.2PS)/6500r/min\n" +
                    "Price: INR 95,130*"
            "Scooter03" -> "Name: RayZR 125 Fi Hybrid\n" +
                    "Engine Type: Air cooled, 4-stroke,SOHC, 2-valve\n" +
                    "Displacement: 125 cc\n" +
                    "Maximum Torque (With Assist Function Working): 10.3 N.m(1.1kgf.m)/5000 r/min\n" +
                    "Maximum Horse Power: 6.0kW(8.2PS)/6500r/min\n" +
                    "Starting System Type: Electric starter and kick starter\n" +
                    "Price: INR 84,730*"
            else -> "Error in fetching details..."
        }

        val inflater = layoutInflater
        val layout = inflater.inflate(
            R.layout.custom_toast_layout,
            findViewById(R.id.customToastLayout)
        )

        val text = layout.findViewById<TextView>(R.id.customToastTextView)
        text.text = toastMessage

        with(Toast(applicationContext)) {
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }

        //Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
    }
}
