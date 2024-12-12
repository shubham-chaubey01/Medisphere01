package com.example.healthcare2.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.healthcare2.R

class DeliveryActivity : AppCompatActivity() {

    private lateinit var uploadPdfButton: Button
    private lateinit var orderNowButton: Button
    private lateinit var addressInput: EditText

    private val pickPdfLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the selected PDF URI
        if (uri != null) {
            Toast.makeText(this, "PDF selected: $uri", Toast.LENGTH_SHORT).show()
            // You can store or send the URI for further processing here
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        uploadPdfButton = findViewById(R.id.uploadPdfButton)
        orderNowButton = findViewById(R.id.orderNowButton)
        addressInput = findViewById(R.id.addressInput)

        // Handle the PDF upload
        uploadPdfButton.setOnClickListener {
            pickPdfLauncher.launch("application/pdf")
        }

        // Handle the Order Now button click
        orderNowButton.setOnClickListener {
            val address = addressInput.text.toString()
            if (address.isNotEmpty()) {
                Toast.makeText(this, "Order placed successfully! Address: $address", Toast.LENGTH_LONG).show()
                // You can add further functionality here to process the order
            } else {
                Toast.makeText(this, "Please enter a valid address.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
