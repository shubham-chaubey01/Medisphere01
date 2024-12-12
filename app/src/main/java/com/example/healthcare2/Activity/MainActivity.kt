package com.example.healthcare2.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthcare.Adapter.CategoryAdapter
import com.example.healthcare2.Adapter.TopDoctorAdapter
import com.example.healthcare2.R
import com.example.healthcare2.ViewModel.MainViewModel
import com.example.healthcare2.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.findViewById<LinearLayout>(R.id.btnMyAppointments).setOnClickListener {
            startActivity(Intent(this, MyAppointmentActivity::class.java))
        }

        binding.root.findViewById<LinearLayout>(R.id.btnDelivery).setOnClickListener {
            startActivity(Intent(this, DeliveryActivity::class.java))
        }

        binding.optionBtn.setOnClickListener {
            showPopupmenu()
        }

        initCategory()
        initTopDoctors()
    }

    private fun showPopupmenu() {
        val popupMenu = PopupMenu(this, binding.optionBtn)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.option_menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.logout -> {
                    logout()
                    true
                }
                else -> false
            }
        }
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun initTopDoctors() {
        binding.apply {
            progressBarTopDoctors.visibility = View.VISIBLE
            viewModel.doctors.observe(this@MainActivity, Observer { doctors ->
                recyclerViewTopDoctors.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewTopDoctors.adapter = TopDoctorAdapter(doctors)
                progressBarTopDoctors.visibility = View.GONE
            })
            viewModel.loadDoctors()

            doctorListTxt.setOnClickListener {
                startActivity(Intent(this@MainActivity, TopDoctorsActivity::class.java))
            }
        }
    }

    private fun initCategory() {
        binding.progressBar3.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer { categories ->
            binding.viewCategory.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.viewCategory.adapter = CategoryAdapter(categories)
            binding.progressBar3.visibility = View.GONE
        })
        viewModel.loadCategory()
    }
}
