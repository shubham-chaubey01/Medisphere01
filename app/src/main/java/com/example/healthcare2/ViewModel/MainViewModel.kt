package com.example.healthcare2.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthcare2.Domain.CategoryModel
import com.example.healthcare2.Domain.DoctorModels
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel(): ViewModel() {
    private val firebaseDatabase=FirebaseDatabase.getInstance()
    private val _category=MutableLiveData<MutableList<CategoryModel>>()
    private val _doctors =MutableLiveData<MutableList<DoctorModels>>()


    val category: LiveData<MutableList<CategoryModel>> =_category
    val doctors: LiveData<MutableList<DoctorModels>> =_doctors


    fun loadCategory(){
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists=mutableListOf<CategoryModel>()
                for(childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(CategoryModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                }
                _category.value=lists
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun loadDoctors(){

        val Ref=firebaseDatabase.getReference("Doctors")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<DoctorModels>()

                for (childSnapshot in  snapshot.children) {
                    val list=childSnapshot.getValue(DoctorModels::class.java)
                    if (list!=null){
                        lists.add(list)
                    }
                }

                _doctors.value=lists

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        )

    }

}