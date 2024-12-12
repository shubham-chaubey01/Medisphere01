package com.example.healthcare2.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare2.Domain.Prescription
import com.example.healthcare2.R

class AppointmentAdapter(private val prescriptionList: List<Prescription>) :
    RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prescription, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val prescription = prescriptionList[position]

        // Set text for TextViews
        holder.appointmentIdTextView.text = "Appointment ID: ${prescription.appointment_id}"
        holder.createdAtTextView.text = "Created At: ${prescription.created_at}"
        holder.medicineNameTextView.text = "Medicine Name: ${prescription.medicine_name}"
        holder.doseTextView.text = "Dose: ${prescription.dose}"
        holder.durationTextView.text = "Duration: ${prescription.duration}"
        holder.frequencyTextView.text = "Frequency: ${prescription.frequency}"
        holder.instructionsTextView.text = "Instructions: ${prescription.instructions}"
        holder.statusTextView.text = "Status: ${prescription.status}"

        // Handle download button click
        holder.downloadButton.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(prescription.url) // Use the URL from the Prescription object
            }
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return prescriptionList.size
    }

    inner class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appointmentIdTextView: TextView = itemView.findViewById(R.id.textViewAppointmentId)
        val createdAtTextView: TextView = itemView.findViewById(R.id.textViewCreatedAt)
        val medicineNameTextView: TextView = itemView.findViewById(R.id.textViewMedicineName)
        val doseTextView: TextView = itemView.findViewById(R.id.textViewDose)
        val durationTextView: TextView = itemView.findViewById(R.id.textViewDuration)
        val frequencyTextView: TextView = itemView.findViewById(R.id.textViewFrequency)
        val instructionsTextView: TextView = itemView.findViewById(R.id.textViewInstructions)
        val statusTextView: TextView = itemView.findViewById(R.id.textViewStatus)
        val downloadButton: Button = itemView.findViewById(R.id.buttonDownloadPdf)
    }
}
