package com.example.healthcare2.Domain

data class Prescription(
    val appointment_id: String = "",
    val created_at: String = "",
    val dose: String = "",
    val duration: String = "",
    val frequency: String = "",
    val instructions: String = "",
    val medicine_name: String = "",
    val prescription_id: String = "",
    val status: String = "",
    val url: String = "" // Add URL field
)
