package com.example.healthcare2.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare2.Domain.Prescription
import com.example.healthcare2.R
import com.example.healthcare2.Adapter.AppointmentAdapter
import com.google.firebase.database.*

class MyAppointmentActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var prescriptionList: MutableList<Prescription>

    // Firebase Realtime Database reference
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_appointment)

        // Initialize Firebase
        database = FirebaseDatabase.getInstance().reference

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewAppointments)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the prescription list
        prescriptionList = mutableListOf()

        // Initialize the adapter
        appointmentAdapter = AppointmentAdapter(prescriptionList)
        recyclerView.adapter = appointmentAdapter

        // Fetch data from Firebase
        fetchPrescriptionsFromFirebase()
    }

    private fun fetchPrescriptionsFromFirebase() {
        // Reference to the "prescriptions" node in Firebase
        val prescriptionsRef = database.child("prescriptions")

        // Listening for changes in the "prescriptions" node
        prescriptionsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                prescriptionList.clear()

                // Check if there are any children (prescriptions) in the snapshot
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val prescription = dataSnapshot.getValue(Prescription::class.java)
                        prescription?.let {
                            prescriptionList.add(it)
                        }
                    }
                    // Notify the adapter that data has changed
                    appointmentAdapter.notifyDataSetChanged()
                } else {
                    // Show a message if there are no prescriptions available
                    Toast.makeText(applicationContext, "No prescriptions found.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Show error message when the data fetch fails
                Toast.makeText(applicationContext, "Failed to load prescriptions", Toast.LENGTH_SHORT).show()
                Log.e("MyAppointmentActivity", "Error: ${error.message}")
            }
        })
    }
}
