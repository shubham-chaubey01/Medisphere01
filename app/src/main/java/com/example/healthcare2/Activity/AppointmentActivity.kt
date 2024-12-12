package com.example.healthcare2.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.healthcare2.R
import com.google.firebase.database.FirebaseDatabase

class AppointmentActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var etMobile: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)

        // Initialize views
        etName = findViewById(R.id.et_name)
        etAge = findViewById(R.id.et_age)
        etMobile = findViewById(R.id.et_mobile)
        radioGroup = findViewById(R.id.radioGroup)
        btnSubmit = findViewById(R.id.button2)

        // Set click listener for the Submit button
        btnSubmit.setOnClickListener {
            saveDataToFirebase()
        }
    }

    private fun saveDataToFirebase() {
        // Get input data
        val name = etName.text.toString().trim()
        val age = etAge.text.toString().trim()
        val mobile = etMobile.text.toString().trim()
        val selectedGenderId = radioGroup.checkedRadioButtonId

        // Validation
        if (name.isEmpty() || age.isEmpty() || mobile.isEmpty() || selectedGenderId == -1) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val gender = findViewById<RadioButton>(selectedGenderId).text.toString()

        // Firebase Database reference
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("Appointments")

        // Create a data model
        val appointmentId = reference.push().key ?: return
        val appointmentData = AppointmentModel(appointmentId, name, age, gender, mobile)

        // Store data in Firebase
        reference.child(appointmentId).setValue(appointmentData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show()
                    clearFields()
                } else {
                    Toast.makeText(this, "Failed to book appointment", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun clearFields() {
        etName.text.clear()
        etAge.text.clear()
        etMobile.text.clear()
        radioGroup.clearCheck()
    }
}

// Data model class
data class AppointmentModel(
    val id: String,
    val name: String,
    val age: String,
    val gender: String,
    val mobile: String
)