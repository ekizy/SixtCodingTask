package com.ekiz.sixtcodingtask.data.api.responsemodels

import com.ekiz.sixtcodingtask.data.uimodels.CarUIModel
import com.ekiz.sixtcodingtask.data.uimodels.FuelType
import com.ekiz.sixtcodingtask.data.uimodels.InnerCleanlinessType
import com.ekiz.sixtcodingtask.data.uimodels.TransmissionType
import com.google.gson.annotations.SerializedName

data class CarAPIModel(@SerializedName("id")
                       private val id: String? = null,
                       @SerializedName("modelIdentifier")
                       private val modelIdentifier: String? = null,
                       @SerializedName("modelName")
                       private val modelName: String? = null,
                       @SerializedName("name")
                       private val name: String? = null,
                       @SerializedName("make")
                       private val make: String? = null,
                       @SerializedName("group")
                       private val group: String? = null,
                       @SerializedName("color")
                       private val color: String? = null,
                       @SerializedName("series")
                       private val series: String? = null,
                       @SerializedName("fuelType")
                       private val fuelType: String? = null,
                       @SerializedName("fuelLevel")
                       private val fuelLevel: String? = null,
                       @SerializedName("transmission")
                       private val transmissionType: String? = null,
                       @SerializedName("licensePlate")
                       private val licensePlate: String? = null,
                       @SerializedName("latitude")
                       private val latitude: Double? = null,
                       @SerializedName("longitude")
                       private val longitude: Double? = null,
                       @SerializedName("innerCleanliness")
                       private val innerCleanliness: String? = null,
                       @SerializedName("carImageUrl")
                       private val carImageUrl: String? = null) {

    fun toUIModel(): CarUIModel = CarUIModel(
            id,
            modelIdentifier,
            modelName,
            name,
            make,
            group,
            color,
            series,
            getFuelType(),
            fuelLevel,
            getTransmissionType(),
            licensePlate,
            latitude,
            longitude,
            getInnerCleanlinessType(),
            carImageUrl
    )

    private fun getTransmissionType() : TransmissionType? {
       return when (transmissionType) {
            "A" -> TransmissionType.AUTOMATIC
            "M" -> TransmissionType.MANUAL
            else -> null
        }
    }

    private fun getInnerCleanlinessType() : InnerCleanlinessType? {
        return when (innerCleanliness) {
            "VERY_CLEAN" -> InnerCleanlinessType.VERY_CLEAN
            "CLEAN" -> InnerCleanlinessType.CLEAN
            "REGULAR" -> InnerCleanlinessType.REGULAR
            else -> null
        }
    }

    private fun getFuelType() : FuelType? {
        return when (fuelType) {
            "P" -> FuelType.PETROL
            "D" -> FuelType.DIESEL
            "E" -> FuelType.ETHANOL
            else -> null
        }
    }

}