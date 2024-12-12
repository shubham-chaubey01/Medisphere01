package com.example.healthcare2.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide

import com.example.healthcare2.Domain.DoctorModels
import com.example.healthcare2.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item:DoctorModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)


        setContentView(binding.root)


        getBundle()

        }

    private fun getBundle() {
          item = intent.getParcelableExtra("object")!!

        binding.apply {
            titleTxt.text=item.Name
            specialTxt.text=item.Special
            patientsTxt.text=item.Patients
            bioTxt.text=item.Biography
            addressTxt.text=item.Address
            experienceTxt.text=item.Exprience.toString()+"Years"
            RatingTxt.text="${item.Rating}"
            backBtn.setOnClickListener{ finish()
            }
              wabsiteBtn.setOnClickListener{
                  val i =Intent(Intent.ACTION_VIEW)
                  i.setData(Uri.parse(item.Site))
                  startActivity(intent )

              }

            messageBtn.setOnClickListener{
                val uri=Uri.parse("smsto:${item.Mobile}")
                val intent=Intent(Intent.ACTION_SENDTO,uri)
                intent.putExtra("sms_body","the SMS text")
                startActivity(intent)
            }

            callBtn.setOnClickListener{
                val uri="tel:"+item.Mobile.trim()
                val intent=Intent(Intent.ACTION_DIAL,
                    Uri.parse(uri))
                startActivity(intent)
            }
            directionBtn.setOnClickListener{
                val intent=Intent(Intent.ACTION_VIEW,
                    Uri.parse(item.Location))
                startActivity(intent)
            }
            shareBtn.setOnClickListener{
                val intent=Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT,item.Name)
                intent.putExtra(
                    Intent.EXTRA_TEXT,item.Name+" "+item.Address+" "+item.Mobile)

                startActivity(Intent.createChooser(intent,"Choose one"))

            }

            makeBtn.setOnClickListener {
                startActivity(Intent(this@DetailActivity, AppointmentActivity::class.java))
            }

            Glide.with(this@DetailActivity)
                .load(item.Picture)
                .into(img)
        }

    }
}
