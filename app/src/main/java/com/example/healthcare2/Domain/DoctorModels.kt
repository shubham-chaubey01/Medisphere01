package com.example.healthcare2.Domain

import android.os.Parcel
import android.os.Parcelable

data class DoctorModels(
    val Address:String="",
    val Biography:String="",
    val Id:Int=0,
    val Name:String="",
    val Picture:String="",
    val Special:String="",
    val Exprience:Int=0,
    val Location:String="",
    val Mobile:String="",
    val Patients:String="",
    val Rating: Double=0.0,
    val Site:String=""


):Parcelable{
    constructor(parcel: Parcel):this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString()


    ){

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Address)
        parcel.writeString(Biography)
        parcel.writeInt(Id)
        parcel.writeString(Name)
        parcel.writeString(Picture)
        parcel.writeString(Special)
        parcel.writeInt(Exprience)
        parcel.writeString(Location)
        parcel.writeString(Mobile)
        parcel.writeString(Patients)
        parcel.writeDouble(Rating)
        parcel.writeString(Site)


    }

    override fun describeContents(): Int {
       return 0

    }


    companion object CREATOR : Parcelable.Creator<DoctorModels> {
        override fun createFromParcel(parcel: Parcel): DoctorModels {
            return DoctorModels(parcel)

        }

        override fun newArray(size: Int): Array<DoctorModels?> {
            return arrayOfNulls(size)

        }
    }

    fun createFromParcel(parcel: Parcel):DoctorModels{
        return DoctorModels(parcel)
    }

    fun newArray(size:Int): Array<DoctorModels?>{
        return arrayOfNulls(size)

    }

    fun add(function: () -> DoctorModels) {

    }
}
