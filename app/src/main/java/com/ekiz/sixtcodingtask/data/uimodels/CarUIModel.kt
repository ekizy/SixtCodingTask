package com.ekiz.sixtcodingtask.data.uimodels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CarUIModel(val id: String? = null,
                 val modelIdentifier: String? = null,
                 val modelName: String? = null,
                 val name: String? = null,
                 val make: String? = null,
                 val group: String? = null,
                 val colorType: ColorType? = null,
                 val series: String? = null,
                 val fuelType: FuelType? = null,
                 val fuelLevel: String? = null,
                 val transmissionType: TransmissionType? = null,
                 val licensePlate: String? = null,
                 val latitude: Double? = null,
                 val longitude: Double? = null,
                 val innerCleanlinessType: InnerCleanlinessType? = null,
                 val carImageUrl: String? = null) : Parcelable {

    val brandSeriesText: String
        get() = String.format("%s %s", make, series)

}