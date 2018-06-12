package com.example.schuller.carscharging.model

import com.google.gson.annotations.SerializedName

/**
 * Model
 * Created by schuller on 4/12/18.
 */

class Station(
        @SerializedName("Nume") val name: String,
        @SerializedName("Adresa")val address: String,
        @SerializedName("Judet")val county: String,
        @SerializedName("Latitudine") val latitude: Double,
        @SerializedName("Longitudine")val longitude: Double,
        @SerializedName("email")val email: String)